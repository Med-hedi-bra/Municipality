package com.example.mini_projet.Controller;


import com.example.mini_projet.Dto.AuthenticationRequest;
import com.example.mini_projet.Dto.AuthenticationResponse;
import com.example.mini_projet.Dto.RegisterRequest;
import com.example.mini_projet.Dto.RegisterResponse;
import com.example.mini_projet.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/miniProjet/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request)
    {
          
        RegisterResponse response = authenticationService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);


    }



    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        AuthenticationResponse response = authenticationService.authenticate(request);


        HttpHeaders headers = new HttpHeaders();
        headers.add("AUTHORIZATION", "BEARER " + response.getToken());
        return new ResponseEntity(response.getMessage(),headers,HttpStatus.OK);


    }
}
