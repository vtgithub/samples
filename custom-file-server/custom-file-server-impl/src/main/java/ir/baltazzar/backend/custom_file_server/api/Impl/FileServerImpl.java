package ir.baltazzar.backend.custom_file_server.api.Impl;

import com.google.gson.Gson;
import ir.baltazzar.backend.custom_file_server.service.FileService;
import ir.baltazzar.custom_file_server.api.FileServerApi;
import ir.baltazzar.custom_file_server.model.UploadFileRequest;
import ir.baltazzar.custom_file_server.model.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@RequestMapping(path = "/file-server", consumes = "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
public class FileServerImpl implements FileServerApi {

    @Autowired
    private FileService fileService;
//    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody UploadFileResponse uploadFile(@RequestParam("properties") String fileRequest,
                                                       @RequestParam("file") MultipartFile file ){
        UploadFileRequest uploadFileRequest= new Gson().fromJson(fileRequest, UploadFileRequest.class);
        UploadFileResponse fileResponse = fileService.save(file, uploadFileRequest.getFilePropertiesList());
        return fileResponse;
    }

    @Override
//    @RequestMapping(path = "/{fileName}", method = RequestMethod.GET, produces = "application/octet-stream")
    public @ResponseBody Resource downloadFile(@PathVariable("fileName") String fileName) {
        ByteArrayResource resource = fileService.get(fileName);
        return resource;
    }


}
