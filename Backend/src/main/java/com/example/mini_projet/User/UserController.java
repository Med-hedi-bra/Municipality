package com.example.mini_projet.User;

import com.example.mini_projet.User.UserService;
import com.example.mini_projet.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/municipality/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/update/{cin}")
        public ResponseEntity<String> updateUser(
            @PathVariable(name = "cin") String cin,
            @RequestParam(name = "firstname",required = false) String firstname,
            @RequestParam(name = "lastname",required = false) String lastname,
            @RequestParam(name = "gender",required = false) String gender,
            @RequestParam(name = "birthOfDate",required = false) String dateOfBirth
            ) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateOfBirth);
        boolean test = userService.updateUser(cin,firstname,lastname,gender,date);
        if(!test) return ResponseEntity.status(404).body("Failure to update, User not found");


        return  ResponseEntity.status(200).body("User update successfully");
    }

}
