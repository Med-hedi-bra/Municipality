package com.example.mini_projet.File;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.stream.Stream;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;


    public File store(MultipartFile file) throws IOException, SQLException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = File.builder()
                .fileType(file.getContentType())
                .name(fileName)
                .data(file.getBytes())
                .build();

        System.out.println("=================================");
        System.out.println(file.getContentType());

       return fileRepository.save(FileDB);


    }





    public File getFile(String idFile) {
        return fileRepository.findById(idFile).get();
    }

    public Stream<File> getAllFiles(){
        return fileRepository.findAll().stream();
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



//    public Optional<File> getFileByCin(@PathVariable String cin){
//        List<File> files = fileRepository.findAll();
//        Optional<File> fileByCin =
//                files.stream().filter(file -> file.getCin().equals(userRepository.findByCin(cin).get()))
//                        .findFirst();
//        return fileByCin;
//    }

}
