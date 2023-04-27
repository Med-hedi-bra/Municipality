package com.example.mini_projet.Service;


import com.example.mini_projet.Dto.Request.AuthenticationRequest;
import com.example.mini_projet.Dto.Response.AuthenticationResponse;
import com.example.mini_projet.Dto.Request.RegisterRequest;
import com.example.mini_projet.Dto.Response.RegisterResponse;
import com.example.mini_projet.Repositories.TokenRepository;
import com.example.mini_projet.Repositories.UserRepository;
import com.example.mini_projet.Security.JwtService;
import com.example.mini_projet.models.enums.Role;
import com.example.mini_projet.models.Token;
import com.example.mini_projet.models.enums.TokenType;
import com.example.mini_projet.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public RegisterResponse register(RegisterRequest request)
    {

        var user = User.builder()
                .cin(request.getCin())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .Role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .valid(false)
                .build();



        if (userRepository.findByCin(request.getCin()).isEmpty())
        {
            userRepository.save(user);
            return RegisterResponse.builder()
                    .message("User Registered with Success")
                    .build();
        }
        else
        {
            return RegisterResponse.builder()
                    .message("failed to Register User")
                    .build();
        }


    }





    private void saveUserToken(String jwtToken, User user) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getCin());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }









    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {

        if(userRepository.findByCin(request.getCin()).isPresent())
        {
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getCin(),
                            request.getPassword()));


            var user = userRepository.findByCin(request.getCin()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(jwtToken, user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .message("Login with Success")
                    .build();
        }
        else
        {
            return AuthenticationResponse.builder()
                    .message("Failed to login")
                    .build();

        }

    }

}
