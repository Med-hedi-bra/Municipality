package com.example.mini_projet.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity


@Table(name = "municipality")
public class Municipality {
    @Id
    @NotBlank
    @Size(max = 10)
    private String codeMun;


    @NotBlank
    @Size(max = 50)
    private String secretaire;

    @NotBlank
    @Size(max = 50)
    private String maire;

    @NotBlank
    @Size(max = 50)
    private String president;

    @NotBlank
    @Size(max = 50)
    private String adjoint;

}
