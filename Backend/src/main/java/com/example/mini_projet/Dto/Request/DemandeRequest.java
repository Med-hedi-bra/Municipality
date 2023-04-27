package com.example.mini_projet.Dto.Request;

import com.example.mini_projet.Enums.Demande_Type;
import com.example.mini_projet.Enums.State_Enum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeRequest implements Serializable {
    @NotBlank
    private Demande_Type type;

    @NotBlank
    private State_Enum state;

    private MultipartFile file;

}
