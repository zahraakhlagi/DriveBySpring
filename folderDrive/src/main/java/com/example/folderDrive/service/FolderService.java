package com.example.folderDrive.service;

import com.example.folderDrive.dto.CreateFolder;
import com.example.folderDrive.model.Folder;
import com.example.folderDrive.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;

    public boolean createFolder(User user, CreateFolder createFolder) {

        try {
            if (createFolder.getFolderName().isBlank()
                    || createFolder.getFolderName().isEmpty()
                    || createFolder.getFolderName() == null) {
                throw new IllegalArgumentException("Folder name can not be empty");
            }
            Folder folder = new Folder(createFolder.getFolderName(), user);
            folderRepository.save(folder);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
