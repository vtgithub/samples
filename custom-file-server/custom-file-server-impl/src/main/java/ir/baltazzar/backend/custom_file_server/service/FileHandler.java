package ir.baltazzar.backend.custom_file_server.service;

import ir.baltazzar.custom_file_server.model.UploadFileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileHandler {
    @Value("${files.path}")
    private String filesPath;

    public void uploadFile(String fileName, MultipartFile file, Integer width, Integer height) {
        try {
//            saveUploadedFiles(Arrays.asList(file));
            convertAndUploadFile(file, fileName, width, height);
        } catch (IOException e) {
            e.printStackTrace();
            //todo log
            throw new UploadException(e);
        }
    }

    public ByteArrayResource getFile(String fileName) throws  DownloadException{
        try{
            Path path = Paths.get(filesPath + fileName+".png");
            return new ByteArrayResource(Files.readAllBytes(path));
        }catch (Exception e){
            e.printStackTrace();
            //todo log
            throw new DownloadException(e);
        }


    }


    private void convertAndUploadFile(MultipartFile file, String fileName, Integer width, Integer height) throws IOException {
//        for (MultipartFile file : files) {
//            if (file.isEmpty()) {
//                continue; //next pls
//            }
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(ImageIO.read(new ByteArrayInputStream(file.getBytes())),0, 0, width, height, null);
        graphics.dispose();
        ImageIO.write(resizedImage, "png", new File(filesPath+fileName+".png"));

//        Path path = Paths.get(filesPath + fileName);
//        Files.write(path, bytes);

//        }
    }

}
