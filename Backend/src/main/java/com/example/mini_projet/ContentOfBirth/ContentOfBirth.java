package com.example.mini_projet.ContentOfBirth;

import com.example.mini_projet.File.File;
import com.example.mini_projet.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class ContentOfBirth   {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCob;



//    @OneToOne(targetEntity = User.class )
//    @JoinColumn(name = "cin")
//    private User user;


//    @OneToOne(targetEntity = File.class)
//    @JoinColumn(name = "file_id", referencedColumnName = "id")
//    private File file;
//
//    public Long getFile() {
//        return file.getIdFile();
//    }


}
