package com.example.mini_projet.File;


import com.example.mini_projet.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    private final Path root = Paths.get("src/main/resources/cvs");
    public void init(){
        try {
            if(!Files.exists(root))
                Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public List<File> saveFiles(MultipartFile[] files, User user) throws IOException {
        try {
            List<File> fileList = new ArrayList<File>();
            for (MultipartFile file : files) {
                String fileContentType = file.getContentType();
                String sourceFileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
                String fileName = file.getOriginalFilename()+ UUID.randomUUID();
                Files.copy(file.getInputStream(), this.root.resolve(fileName));
                File fileEntity = File.builder()
                        .name(fileName)
                        .content(sourceFileContent)
                        .fileType(fileContentType)
                        .build();
                fileEntity.setUser(user);
                fileList.add(fileEntity);
            }
            fileRepository.saveAll(fileList);
            return fileList;
        } catch (IOException e) {
            throw e;
        }
    }
}
