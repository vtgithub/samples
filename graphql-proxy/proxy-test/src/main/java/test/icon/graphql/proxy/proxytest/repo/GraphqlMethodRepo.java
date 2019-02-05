package test.icon.graphql.proxy.proxytest.repo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GraphqlMethodRepo {
//    @Value("${micro1.url}")
//    private String micro1Url;
//    @Value("${micro2.url}")
//    private String micro2Url;
//    private Set<String> micro1MethodSet = new HashSet<>();
//    private Set<String> micro2MethodSet = new HashSet<>();

    private Map<String, Set<String>> microMethodsMap = new HashMap<>();

    public void setMicroMethodSet(String url, Set<String> micro1MethodSet) {
        this.microMethodsMap.put(url, micro1MethodSet);
    }

    public Set<String> getMicroMethodSet(String url) {
        return this.microMethodsMap.get(url);
    }


    public Optional<String> getRelatedMicroUrl(String methodName){
        for (Map.Entry<String, Set<String>> specificMicroMethodSet : microMethodsMap.entrySet()) {
            if (isInSet(specificMicroMethodSet.getValue(), methodName))
                return Optional.of(specificMicroMethodSet.getKey());
        }
        return Optional.empty();
    }

    private boolean isInSet(Set<String> set,  String name){
        for (String setMember : set) {
            if (setMember.toLowerCase().contains(name.toLowerCase()))
                return true;
        }
        return false;
    }
}
