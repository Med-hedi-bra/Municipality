package com.example.mini_projet.File;


import com.example.mini_projet.Demande.Demande;
import com.example.mini_projet.Municipality.Municipality;
import com.example.mini_projet.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.jsonwebtoken.io.Decoders;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.io.InputStream;
import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Getter
@Setter
@Builder
public class File {


        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        private String idFile;

        @Column
        private String name;

        @Lob
        private byte[] data;

        @Column
        private String fileType;




        @OneToOne(targetEntity = Demande.class)
        @JoinColumn(name = "id_dem")
        @JsonBackReference
        @Getter
        @Setter
        private Demande demande;







    }
