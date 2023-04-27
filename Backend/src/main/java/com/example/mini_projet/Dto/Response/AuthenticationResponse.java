package com.example.mini_projet.Dto.Response;


import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {


    private String token;

    private String message;


}
