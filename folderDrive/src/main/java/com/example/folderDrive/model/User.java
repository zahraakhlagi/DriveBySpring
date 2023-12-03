package com.example.folderDrive.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstname;
    private String lastname;
    @NonNull
    @Column(unique = true)
    private String email;
    @NonNull
    private String password;
}
