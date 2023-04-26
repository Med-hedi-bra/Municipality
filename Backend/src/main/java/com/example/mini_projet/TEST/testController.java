package com.example.mini_projet.TEST;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/admin")
//@EnableGlobalMethodSecurity( //find solution
//        prePostEnabled = true)

public class testController {

    private final TestService testService;


    public testController(TestService testService) {
        this.testService = testService;
    }


    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List> getUsers()
    {
        return ResponseEntity.ok(testService.getAllUsers());
    }
}
