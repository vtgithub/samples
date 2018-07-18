package ir.baltazzar.backend.custom_file_server.service;

import org.springframework.core.NestedRuntimeException;

public class DownloadException extends NestedRuntimeException {
    public DownloadException(Exception e) {
        super(e.getMessage());
    }
}
