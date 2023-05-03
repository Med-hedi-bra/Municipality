package com.example.mini_projet.File;


import com.example.mini_projet.Municipality.Municipality;
import com.example.mini_projet.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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
        private Long id;
        @Column
        private String name;

        @Lob
        @Column
        private String content;

        @Column
        private String fileType;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "cin")
        @JsonBackReference
        private User user;

        public String getUser() {
                return user.getCin();
        }


    }
