package com.example.folderDrive.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "_folder")
@NoArgsConstructor
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String folderName;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany
    @JoinColumn(name = "fileId")
    private List<File> files;

    public Folder(String folderName, User user) {
        this.folderName = folderName;
        this.user = user;
    }
}
