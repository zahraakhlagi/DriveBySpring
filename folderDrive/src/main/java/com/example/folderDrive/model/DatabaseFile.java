package com.example.folderDrive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatabaseFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String fileName;
    private  String fileType;

    //LOB is datatype for storing large object data
    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
