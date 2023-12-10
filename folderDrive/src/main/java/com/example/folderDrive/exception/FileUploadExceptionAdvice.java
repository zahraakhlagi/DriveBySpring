package com.example.folderDrive.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadFileException.class)
    public ResponseEntity<Object> handleMaxSizeException
            (MaxUploadFileException exc,WebRequest request){
        var error= exc.getMessage();

       return handleExceptionInternal(exc, error,new HttpHeaders()
       , HttpStatusCode.valueOf(400),request);
    }
}
