package com.example.folderDrive.service;

import com.example.folderDrive.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface FolderRepository extends JpaRepository<Folder, UUID> {
}
