package ir.baltazzar.backend.custom_file_server.service;

import ir.baltazzar.custom_file_server.model.FileProperties;
import ir.baltazzar.custom_file_server.model.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileHandler fileHandler;


    public UploadFileResponse save(MultipartFile file, List<FileProperties> filePropertiesList) {

        Map<String, String> fileTags = new HashMap<>();
        String fileName;
        for (FileProperties fileProperties : filePropertiesList) {
            fileName = (fileProperties == null || fileProperties.equals(""))?UUID.randomUUID().toString():fileProperties.getName();
            fileHandler.uploadFile(fileName,file, fileProperties.getWidth(), fileProperties.getHeight());
            fileTags.put(fileProperties.getTag(), fileName);
        }
        return new UploadFileResponse(fileTags);
    }


    public ByteArrayResource get(String fileName) {
        ByteArrayResource byteArrayResource = fileHandler.getFile(fileName);
        return byteArrayResource;
    }
}
