package com.example.folderDrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.folderDrive")
public class DrivefolderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrivefolderApplication.class, args);
	}

}
