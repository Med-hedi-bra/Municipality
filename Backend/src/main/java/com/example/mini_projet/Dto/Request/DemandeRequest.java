package com.example.mini_projet.Dto.Request;

import com.example.mini_projet.Enums.Demande_Type;
import com.example.mini_projet.Enums.State_Enum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeRequest implements Serializable {

    private String title;


    private Demande_Type type;


    private State_Enum state;


    //private MultipartFile file;

}
