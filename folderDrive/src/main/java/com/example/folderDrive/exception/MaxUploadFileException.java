package com.example.folderDrive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class MaxUploadFileException extends RuntimeException{
    public MaxUploadFileException(String message){
        super (message);
    }
}
