package com.example.folderDrive.Controller;

import com.example.folderDrive.dto.Response;
import com.example.folderDrive.model.File;
import com.example.folderDrive.model.User;
import com.example.folderDrive.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    //this endpoint is to upload the file by login user in special folder
    @PostMapping("/uploadFile")
    public Response uploadFile(@AuthenticationPrincipal User user,
                               @RequestParam("file") MultipartFile file,
                               @RequestParam("folder") UUID folderId) {
        File fileName = fileService.storeFile(user, file, folderId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    //this endpoint is to download the file by user , that the file is in a folder
    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@AuthenticationPrincipal User user, @RequestParam UUID fileId, @RequestParam UUID folderId, HttpServletRequest request) throws FileNotFoundException {
        // Load file as Resource
        File databaseFile = fileService.getFile(user, fileId, folderId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }
    //this endpoint is to get all files that user has in your folder

    @GetMapping("/files")
    public ResponseEntity<List<File>> getAllFiles(@AuthenticationPrincipal User user, @RequestParam UUID folderId) throws IllegalAccessException {
        List<File> result = fileService.getAllFiles(user, folderId);
        return ResponseEntity.ok(result);
    }

    //this endpoint is to delete a file by own user
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@AuthenticationPrincipal User user, @RequestParam UUID folderId, @RequestParam UUID fileId) throws FileNotFoundException {
        fileService.deleteFile(user, folderId, fileId);
        return ResponseEntity.ok("File deleted successfully.");
    }

}
