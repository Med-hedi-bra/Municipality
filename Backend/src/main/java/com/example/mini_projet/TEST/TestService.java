package com.example.mini_projet.TEST;


import com.example.mini_projet.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final UserRepository userRepository;



    public List getAllUsers()
    {
        return userRepository.findAll();
    }
}
