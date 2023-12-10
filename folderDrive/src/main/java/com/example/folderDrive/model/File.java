package com.example.folderDrive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "_file")
@Builder
@AllArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "folderId")
    private Folder folder;


}


