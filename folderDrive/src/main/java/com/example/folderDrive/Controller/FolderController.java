package com.example.folderDrive.Controller;

import com.example.folderDrive.dto.CreateFolder;
import com.example.folderDrive.model.User;
import com.example.folderDrive.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;

    //this endpoint is to create a folder by login user
    @PostMapping("/folder")
    public ResponseEntity<String> createAFolder(@AuthenticationPrincipal User user, @RequestBody CreateFolder createFolder) {
        boolean result = folderService.createFolder(user, createFolder);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Folder successfully created");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.ordinal()).body("Somthing went wrong");
        }

    }


}