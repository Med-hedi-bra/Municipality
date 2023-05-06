package com.example.mini_projet.Dto.Response;


import com.example.mini_projet.User.User;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;

    private User user;

    private String message;


}
