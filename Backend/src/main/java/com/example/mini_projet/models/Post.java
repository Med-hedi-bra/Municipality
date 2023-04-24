package com.example.mini_projet.models;


import com.example.mini_projet.enums.Post_Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Enumerated(EnumType.STRING)
    private Post_Type type;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_added;

    @ManyToOne
    @JoinColumn(name = "id_Mun")
    private Municipality municipality;


    @OneToMany(targetEntity = Image.class , mappedBy = "post")
    private ArrayList<Image> images = new ArrayList<Image>();

}
