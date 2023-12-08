package com.example.folderDrive.repositoreis;

import com.example.folderDrive.model.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FileDbRepository extends JpaRepository<DatabaseFile,UUID> {
}
