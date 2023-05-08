package com.example.mini_projet.Post;


import com.example.mini_projet.Municipality.Municipality;
import com.example.mini_projet.Enums.Post_Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPost;

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 3)
    private String content;

    @Enumerated(EnumType.STRING)
    private Post_Type type;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_added;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mun")
    @JsonBackReference
    private Municipality municipality;

    public Integer getMunicipality() {
        return municipality.getIdMun();
    }


//    @OneToMany(targetEntity = Image.class , mappedBy = "post")
//    private ArrayList<Image> images = new ArrayList<>();

}
