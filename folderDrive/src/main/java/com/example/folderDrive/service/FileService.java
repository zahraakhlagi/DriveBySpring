package com.example.folderDrive.service;

import com.example.folderDrive.exception.FileStorageException;
import com.example.folderDrive.exception.FolderNotFoundException;
import com.example.folderDrive.model.File;
import com.example.folderDrive.model.Folder;
import com.example.folderDrive.model.User;
import com.example.folderDrive.repositoreis.FileRepository;
import com.example.folderDrive.repositoreis.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;

    //this method is to upload the file in database by user
    public File storeFile(User user, MultipartFile file, UUID folderId) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Folder folder = folderRepository.findById(folderId)
                    .orElseThrow(() -> new FolderNotFoundException("Folder not found with id" + folderId));


            File newFile =
                    File.builder()
                            .fileName(file.getName())
                            .fileType(file.getContentType())
                            .data(file.getBytes())
                            .folder(folder)
                            .build();

            return fileRepository.save(newFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    //this method is to get file from database by user
    public File getFile(User user, UUID fileId, UUID folderId) throws FileNotFoundException, IllegalArgumentException {
        Folder folder = folderRepository
                .findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find a folder with that id"));
        if (!folder.getUser().getId().equals(user.getId())) {
            throw new FileNotFoundException("You can only download your own file");
        }
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

    //this method is to delete a file by user
    public void deleteFile(User user, UUID folderId, UUID fileId) throws FileNotFoundException {
        Folder folder = folderRepository
                .findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find a folder with that id"));
        if (!folder.getUser().getId().equals(user.getId())) {
            throw new FileNotFoundException("You can only delete your own file");
        }
        fileRepository.deleteById(fileId);
    }

    //this method is , user can get all files
    public List<File> getAllFiles(User user, UUID folderId) throws IllegalAccessException {
        Folder folder = folderRepository
                .findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find a folder with that id"));
        if (!folder.getUser().getId().equals(user.getId())) {
            throw new IllegalAccessException("You do not have a access to this folder");
        }
        return fileRepository.findAll();
    }
}
