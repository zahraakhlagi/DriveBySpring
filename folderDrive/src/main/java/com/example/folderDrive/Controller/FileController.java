package com.example.folderDrive.Controller;

import com.example.folderDrive.dto.ResponseFile;
import com.example.folderDrive.dto.ResponseMessage;
import com.example.folderDrive.model.DatabaseFile;
import com.example.folderDrive.model.User;
import com.example.folderDrive.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileController {

    private final FileStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file) throws IOException {
        DatabaseFile fileName= storageService.uploadFile(user,file);
        /*String fileDownLoadUri= ServletUriComponentsBuilder.fromCurren
        tContexPath()
                .path("/downloadFile")
                .path(fileName.getFileName())
                .toUriString();*/

            String message = "";
        try {
            storageService.uploadFile(user,file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFile(){
        List<ResponseFile> files=storageService
                .getAllFiles()
                .map(dbFile-> {
                    String fileDownloadUri= ServletUriComponentsBuilder
                            .fromCurrentRequestUri()
                            .path("/files/")
                            .path(String.valueOf(dbFile.getId()))
                            .toUriString();

                    return new ResponseFile(
                            dbFile.getFileName(),
                            fileDownloadUri,
                            dbFile.getFileType(),
                            (long) dbFile.getData().length);}).toList();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    }





