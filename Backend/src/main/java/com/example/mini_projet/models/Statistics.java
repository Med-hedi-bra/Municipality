package com.example.mini_projet.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
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



    @OneToOne(targetEntity = Municipality.class )
    @MapsId
    @JoinColumn(name = "id_mun")
    @JsonBackReference
    private Municipality municipality;
}
