package com.example.folderDrive.service;

import com.example.folderDrive.exception.FileStorageException;
import com.example.folderDrive.exception.MaxUploadFileException;
import com.example.folderDrive.model.DatabaseFile;
import com.example.folderDrive.model.User;
import com.example.folderDrive.repositoreis.FileDbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class FileStorageService {
    private final FileDbRepository fileDbRepository;

    //receives MultipartFile object, transform to FileDb object and save to database
    public DatabaseFile uploadFile(User user, MultipartFile file) throws MaxUploadFileException,FileStorageException {
//Normalize filename
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("sorry! Filename contains invalid path sequence " + fileName);
            }
            DatabaseFile fileDb =
                    DatabaseFile.builder()
                            .fileName(fileName)
                            .fileType(file.getContentType())
                            .data(file.getBytes())
                            .user(user)
                            .build();
            return fileDbRepository.save(fileDb);
        }catch (IOException e){
            throw new FileStorageException("Could not upload file "+ fileName+ ". Please try again!", e);
        }
    }

    /*//returns a FileDb by provided id
    public DatabaseFile getFile(UUID id){
        return fileDbRepository.findById(id).get();

    }*/
    //returns all stored files as list of code FileDb objects
    public Stream<DatabaseFile> getAllFiles(){
        return fileDbRepository.findAll().stream();
    }
}
