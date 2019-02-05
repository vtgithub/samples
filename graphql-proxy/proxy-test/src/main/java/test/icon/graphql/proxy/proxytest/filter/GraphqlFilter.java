package test.icon.graphql.proxy.proxytest.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.icon.graphql.proxy.proxytest.dto.GraphqlRequest;
import test.icon.graphql.proxy.proxytest.service.GraphqlService;
import test.icon.graphql.proxy.proxytest.service.ServletCustomTool;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GraphqlFilter extends ZuulFilter {

    @Autowired
    private ServletCustomTool servletCustomTool;
    @Autowired
    private GraphqlService graphqlService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run(){
        RequestContext currentContext = RequestContext.getCurrentContext();
        try {
            HttpServletRequest request = currentContext.getRequest();
            GraphqlRequest graphqlRequest = graphqlService.getGraphqlRequest(servletCustomTool.getRequestBody(request));
            List<Object> responseList = new ArrayList<>();
            for (String query : graphqlRequest.getReqList()) {
                String url = graphqlService.getRelatedMicroserviceUrl(query);
                responseList.add(servletCustomTool.sendQueryOrMutation(url, query));
            }
            servletCustomTool.prepareResponse(currentContext, 200, responseList);
        } catch (IOException e) {
            e.printStackTrace();
            servletCustomTool.prepareResponse(currentContext, 400, "bad request");
        }
        return null;
    }
}
