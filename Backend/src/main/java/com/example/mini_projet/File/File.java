package com.example.mini_projet.File;


import com.example.mini_projet.Municipality.Municipality;
import com.example.mini_projet.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Getter
@Setter
@Builder

public class File {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long idFile;

        @Column
        private String name;

        @Lob
        @Column
        private byte[] data;

        @Column
        private String fileType;


        @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
        @JoinColumn(name = "cin")
        @JsonBackReference
        @Getter
        private User user;

        public String getCin() {
                return user.getCin();
        }




    }
