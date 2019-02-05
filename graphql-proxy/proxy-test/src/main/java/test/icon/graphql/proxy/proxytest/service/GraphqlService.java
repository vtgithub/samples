package test.icon.graphql.proxy.proxytest.service;

import com.google.gson.Gson;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import test.icon.graphql.proxy.proxytest.dto.GraphqlRequest;
import test.icon.graphql.proxy.proxytest.repo.GraphqlMethodRepo;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class GraphqlService {

    Logger logger = Logger.getLogger("logger");

    private Gson gson = new Gson();
    @Autowired
    private GraphqlMethodRepo graphqlMethodRepo;
    @Autowired
    private GraphqlConfig graphqlConfig;

    public GraphqlRequest getGraphqlRequest(String body) {
        GraphqlRequest graphqlRequest = gson.fromJson(body, GraphqlRequest.class);
        return graphqlRequest;
    }
    public String getRelatedMicroserviceUrl(String graphqlQuery){
            Optional<String> relatedUrl = graphqlMethodRepo.getRelatedMicroUrl(getMethodName(graphqlQuery));
        if (!relatedUrl.isPresent())
            throw new InvalidQueryException("method name not found");
        return relatedUrl.get();
    }

    private String getMethodName(String graphqlQuery){
        graphqlQuery = graphqlQuery.replace("query", "");
        graphqlQuery = graphqlQuery.replace("mutation", "");
        graphqlQuery = graphqlQuery.trim();
        graphqlQuery = graphqlQuery.substring(graphqlQuery.indexOf('{') +1, graphqlQuery.indexOf('('));
        return graphqlQuery;
    }


    public void initMethodsByBasePackage() {
        for (GraphServerProperties gsp : graphqlConfig.getMicroList()) {
            graphqlMethodRepo.setMicroMethodSet(gsp.getUrl(), getMethodSetFromDependency(gsp.getBasePackage()));
        }
    }

    private Set<String> getMethodSetFromDependency(String packageName){
        Set<String> methodNameSet = new HashSet<>();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(RequestMapping.class);
        for (Class<?> controller : controllers) {
            for (Method method : controller.getMethods()) {
                methodNameSet.add(method.getName());
            }
        }
        return methodNameSet;
    }

    private Set<String> getMethodSetFromSchemaFile(String filePath) throws IOException {
        Set<String> methodList = new HashSet<>();
        String schema = new String(Files.readAllBytes(Paths.get(filePath)));
        methodList.addAll(fetchMethods(filePath, schema, "Query"));
        methodList.addAll(fetchMethods(filePath, schema, "Mutation"));
        return methodList;
    }

    private Set<String> fetchMethods(String filePath, String schema, String type) {
        Set<String> methodSet = new HashSet<>();
        if (schema.contains(type)){
            schema = schema.substring(schema.indexOf("type "+type)).trim();
            String[] queries = schema.substring(schema.indexOf('{')+1, schema.indexOf('}')).split("\\n");
            for (String query : queries) {
                if (query.contains(":")){
                    query = query.trim();
                    query = query.substring(0, query.indexOf(':')).trim();
                    if (query.contains("("))
                        query = query.substring(0,query.indexOf('('));
                    methodSet.add(query);
                }
            }
        }
        logger.info("___________fetched from file -> " + filePath + " "+type+"s : " + gson.toJson(methodSet)+"___________");
        return methodSet;
    }

    public void initMethodsBySchemaFiles() throws FileReadingException {
        for (GraphServerProperties gsp : graphqlConfig.getMicroList()) {
            try {
                graphqlMethodRepo.setMicroMethodSet(gsp.getUrl(), getMethodSetFromSchemaFile(gsp.getSchemaFilePath()));
            } catch (IOException e) {
                throw new FileReadingException(e);
            }
        }
    }
}
