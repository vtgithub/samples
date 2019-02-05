package test.icon.graphql.proxy.proxytest.service;

import java.io.IOException;

public class FileReadingException extends Exception{
    public FileReadingException(Exception e) {
        super(e);
    }

    public FileReadingException() {
    }
}
