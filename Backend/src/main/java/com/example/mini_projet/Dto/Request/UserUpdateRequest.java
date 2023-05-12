package com.example.mini_projet.Dto.Request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private String cin;

    private String firstname;


    private String lastname;

    private String gender;


    private Date dateOfBirth;


    private String email;


    private String password;

    @Enumerated(EnumType.STRING)
    private com.example.mini_projet.Enums.Role role;
}
