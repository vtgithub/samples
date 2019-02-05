package test.icon.graphql.proxy.proxytest.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "graphql")
public class GraphqlConfig {
    private List<GraphServerProperties> microList;

    public List<GraphServerProperties> getMicroList() {
        return microList;
    }
    public void setMicroList(List<GraphServerProperties> microList) {
        this.microList = microList;
    }
}
