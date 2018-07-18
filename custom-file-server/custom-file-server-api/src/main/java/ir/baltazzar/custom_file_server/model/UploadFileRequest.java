package ir.baltazzar.custom_file_server.model;

import java.util.List;

public class UploadFileRequest {
    List<FileProperties> filePropertiesList;

    public List<FileProperties> getFilePropertiesList() {
        return filePropertiesList;
    }

    public void setFilePropertiesList(List<FileProperties> filePropertiesList) {
        this.filePropertiesList = filePropertiesList;
    }
}
