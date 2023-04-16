package com.example.mini_projet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "municipality")
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String secritaire;

    @NotBlank
    @Size(min = 3)
    private String Maitre;
    @NotBlank
    @Size(min = 3)
    private String president;

    @OneToMany(mappedBy = "municipality")
    private ArrayList<User> users = new ArrayList<User>();


    @OneToOne(targetEntity = Statistics.class)
    @JoinColumn(name = "id_stat")
    private Statistics statistics;


    @OneToMany(targetEntity = Post.class , mappedBy = "municipality")
    private ArrayList<Post> posts = new ArrayList<Post>();


}
