package com.example.mini_projet.Dto;


import com.example.mini_projet.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Size(max = 15)
    private String firstname;


    @NotBlank
    @Size(max = 15)
    private String lastname;


    @NotBlank
    @Size(max = 20)

    private String cin;

    private Role Role;


    @NotBlank
    private String password;






}
