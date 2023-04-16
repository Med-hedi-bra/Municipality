package com.example.mini_projet.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private int population;

    @NotBlank
    private int surface;

    @NotBlank
    private int age;

    @NotBlank
    private int program;


    @OneToOne(targetEntity = Municipality.class , mappedBy = "statistics")
    private Municipality municipality;
}
