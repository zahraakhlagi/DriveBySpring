package com.example.folderDrive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class FileStorageException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public FileStorageException(String message){
        super(message);
    }
    public FileStorageException(String message, Throwable cause){
        super(message, cause);
    }
}
