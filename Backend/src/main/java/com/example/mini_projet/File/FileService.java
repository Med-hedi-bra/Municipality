package com.example.mini_projet.File;


import com.example.mini_projet.Statistics.Statistics;
import com.example.mini_projet.User.User;
import com.example.mini_projet.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    private UserRepository userRepository;

    private final Path root = Paths.get("src/main/resources/cvs");

    public void init(){
        try {
            if(!Files.exists(root))
                Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

//    public List<File> saveFiles(MultipartFile[] files, User user) throws IOException {
//        try {
//            var userCin = user.getCin();
//            List<File> fileList = new ArrayList<File>();
//            for (MultipartFile file : files) {
//                String fileContentType = file.getContentType();
//                String sourceFileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
//                String fileName = file.getOriginalFilename()+ UUID.randomUUID();
//                Files.copy(file.getInputStream(), this.root.resolve(fileName));
//                File fileEntity = File.builder()
//                        .name(fileName)
//                        .content(sourceFileContent)
//                        .fileType(fileContentType)
//                        .user(user)
//                        .build();
//                fileList.add(fileEntity);
//            }
//            fileRepository.saveAll(fileList);
//            return fileList;
//        } catch (IOException e) {
//            throw e;
//        }
//    }

    public File store(MultipartFile file, User user) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = File.builder()
                .fileType(file.getContentType())
                .user(user)
                .name(fileName)
                .data(file.getBytes())
                .build();

        return fileRepository.save(FileDB);
    }

    public File getFile(Long idFile) {
        return fileRepository.findById(idFile).get();
    }

    public Stream<File> getAllFiles(){
        return fileRepository.findAll().stream();
    }

    public Optional<File> getFileByCin(@PathVariable String cin){
        List<File> files = fileRepository.findAll();
        Optional<File> fileByCin =
                files.stream().filter(file -> file.getCin().equals(userRepository.findByCin(cin).get()))
                        .findFirst();
        return fileByCin;
    }

}
