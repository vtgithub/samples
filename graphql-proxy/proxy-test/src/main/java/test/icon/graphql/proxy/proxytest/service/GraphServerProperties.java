package test.icon.graphql.proxy.proxytest.service;

public class GraphServerProperties {
    private String url;
    private String basePackage;
    private String schemaFilePath;

    public String getSchemaFilePath() {
        return schemaFilePath;
    }

    public void setSchemaFilePath(String schemaFilePath) {
        this.schemaFilePath = schemaFilePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
