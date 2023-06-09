package com.example.mini_projet.Auth;



import com.example.mini_projet.Dto.Request.AuthenticationRequest;
import com.example.mini_projet.Dto.Response.AuthenticationResponse;
import com.example.mini_projet.Dto.Request.RegisterRequest;
import com.example.mini_projet.Dto.Response.RegisterResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/municipality/auth")
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
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request,
                                                               HttpServletRequest req)
    {
        AuthenticationResponse response = authenticationService.authenticate(request);
        int aux;

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("AUTHORIZATION", "BEARER " + response.getToken());
//        return new ResponseEntity(response.getMessage(),headers,HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);


    }
}
