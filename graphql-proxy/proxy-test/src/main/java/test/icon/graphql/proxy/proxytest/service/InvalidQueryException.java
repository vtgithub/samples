package test.icon.graphql.proxy.proxytest.service;

public class InvalidQueryException extends RuntimeException {
    public InvalidQueryException(String message) {
        super(message);
    }

    public InvalidQueryException(Throwable cause) {
        super(cause);
    }
}
