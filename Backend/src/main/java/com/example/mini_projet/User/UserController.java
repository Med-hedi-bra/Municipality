package com.example.mini_projet.User;

import com.example.mini_projet.Dto.Request.UserUpdateRequest;
import com.example.mini_projet.File.message.ResponseMessage;
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
@CrossOrigin(origins = "*")
public class UserController {

    final UserService userService;
    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping(path = "/{cin}")
    public User getUsersByCin(@PathVariable("cin") String cin){
        return userService.getByCin(cin);

    }


    @GetMapping(path = "/{codeMun}")
    public List<User> getUsersByCodeMun(@PathVariable("codeMun") Integer codeMun){
        return userService.getUsersByCodeMun(codeMun);

    }


    @PutMapping("/update1/{cin}")
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


    @PutMapping("/update/{cin}")
    public ResponseEntity update(@PathVariable("cin") String cin , @RequestBody UserUpdateRequest userUpdateRequest){
            boolean test = userService.update(cin , userUpdateRequest);
            if(test) return ResponseEntity.status(203).body(new ResponseMessage("Update user with success"));
            else return ResponseEntity.status(451).body(new ResponseMessage("Failure to Update user"));


    }


    // a function that updates the status of user(enabled/disabled) : used by ADMIN
    // please remove the comments to use this feature

    //    @PutMapping("/updateStatus/{cin}")
//    public ResponseEntity update(@PathVariable("cin") String cin , @RequestParam boolean status){
//        boolean test = userService.updateStatus(cin , status);
//        if(test) return ResponseEntity.status(203).body(new ResponseMessage("Update user with success"));
//        else return ResponseEntity.status(451).body(new ResponseMessage("Failure to Update user"));
//
//
//    }
}
