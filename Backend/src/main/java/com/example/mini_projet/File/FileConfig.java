package com.example.mini_projet.File;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfig {
    @Bean
    CommandLineRunner initFiles(FileService fileService) {
        return args -> {
            fileService.init();
        };
    }
}
