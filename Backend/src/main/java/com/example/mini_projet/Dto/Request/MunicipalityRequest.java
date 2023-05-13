package com.example.mini_projet.Dto.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipalityRequest {
    private Integer idMun;

    //@NotBlank

    private String secritaire;


    private String maitre;


    private String president;

}
