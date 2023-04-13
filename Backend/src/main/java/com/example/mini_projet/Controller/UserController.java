package com.example.mini_projet.Controller;

import com.example.mini_projet.Service.UserService;
import com.example.mini_projet.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    @GetMapping("/")

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
