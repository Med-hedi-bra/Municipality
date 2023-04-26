package com.example.mini_projet.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer population;

    private Integer surface;

    private Integer age;

    private Integer program;


//    @OneToOne(targetEntity = Municipality.class )
//    @JoinColumn(name = "id_mun")
//    private Municipality municipality;
}
