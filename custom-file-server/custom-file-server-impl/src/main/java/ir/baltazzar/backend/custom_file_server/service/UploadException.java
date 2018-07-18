package ir.baltazzar.backend.custom_file_server.service;

import org.springframework.core.NestedRuntimeException;

public class UploadException extends NestedRuntimeException {
    public UploadException(Exception e) {
        super(e.getMessage());
    }
}
