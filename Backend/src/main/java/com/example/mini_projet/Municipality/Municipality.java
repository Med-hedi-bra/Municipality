package com.example.mini_projet.Municipality;

import com.example.mini_projet.Post.Post;
import com.example.mini_projet.Statistics.Statistics;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String maitre;
    @NotBlank
    @Size(min = 3)
    private String president;

//    @OneToMany(mappedBy = "municipality" ,fetch = FetchType.LAZY)
//    private List<User> users ;


    @OneToOne(targetEntity = Statistics.class,mappedBy = "municipality" , fetch = FetchType.EAGER)
    @JsonManagedReference
    private Statistics statistics;


    @OneToMany(targetEntity = Post.class , mappedBy = "municipality" ,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Post> posts;



}
