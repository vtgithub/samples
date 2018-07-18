package ir.baltazzar.backend.custom_file_server.api;

import ir.baltazzar.backend.custom_file_server.service.DownloadException;
import ir.baltazzar.backend.custom_file_server.service.UploadException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({UploadException.class, DownloadException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public void handleApiValidationError(Exception e) {
        //todo log
        e.printStackTrace();
//        List<String> errorMessages = new ArrayList<String>();
//        for (ObjectError obje ctError : e.getBindingResult().getAllErrors()) {
//            errorMessages.add(buildMessage(objectError));
//        }
//        return errorMessages;
    }



}
