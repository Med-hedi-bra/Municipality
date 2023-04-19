package com.example.mini_projet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Lob
    private Blob file;


    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "id_post")
    private Post post;
}
