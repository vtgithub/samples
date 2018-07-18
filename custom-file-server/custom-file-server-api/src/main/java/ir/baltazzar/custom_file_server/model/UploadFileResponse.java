package ir.baltazzar.custom_file_server.model;

import java.util.Map;

public class UploadFileResponse {
    private Map<String,String> fileTags;

    public UploadFileResponse() {
    }

    public UploadFileResponse(Map<String, String> fileTags) {
        this.fileTags = fileTags;
    }

    public Map<String, String> getFileTags() {
        return fileTags;
    }

    public void setFileTags(Map<String, String> fileTags) {
        this.fileTags = fileTags;
    }
}
