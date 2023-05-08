package com.example.mini_projet.Dto.Request;



import com.example.mini_projet.Enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class RegisterRequest {

    @NotBlank
    @Size(max = 15)
    private String cin;


    @NotBlank
    @Size(max = 15)
    private String firstname;

    @NotBlank
    @Size(max = 15)
    private String lastname;

    @NotBlank
    @Size(max = 50)
    private String gender;


//    private Role role;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    private String password;

    //@NotBlank
    private Integer codeMun;






}
