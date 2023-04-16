package com.example.mini_projet.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String Title;

    @NotBlank
    @Size(min = 3)
    private String content;

    @NotBlank
    private String type;

    @NotBlank
    private Date date_added;

    @ManyToOne
    @JoinColumn(name = "id_Mun")
    private Municipality municipality;


    @OneToMany(targetEntity = Image.class , mappedBy = "post")
    private ArrayList<Image> images = new ArrayList<Image>();

}
