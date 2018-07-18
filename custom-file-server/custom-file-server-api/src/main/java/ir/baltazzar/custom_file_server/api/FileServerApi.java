package ir.baltazzar.custom_file_server.api;

import ir.baltazzar.custom_file_server.model.UploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/file-server", consumes = "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
public interface FileServerApi {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody UploadFileResponse uploadFile(@RequestParam("properties") String uploadFileRequest, @RequestParam("file") MultipartFile multipartFile);

    @RequestMapping(path = "/{fileName}", method = RequestMethod.GET, produces = "application/octet-stream")
    @ResponseBody Resource downloadFile(@PathVariable("fileName") String fileName);

}
