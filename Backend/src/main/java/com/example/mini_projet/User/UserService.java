package com.example.mini_projet.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;

    @Autowired

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getByCin(String userCin){
        Optional<User> user = userRepository.findByCin(userCin);
        if(user.isPresent()) return user.get();
        throw new IllegalStateException("User not found");
    }


    // function that update the user credentials
    @Transactional
    public boolean updateUser(String cin ,
                              String firstname ,
                              String lastname,
                              String gender,
                              Date dateOfBirth
                              ) {
        Optional<User> user = userRepository.findByCin(cin);
        if(user.isEmpty()) {
            return false;
        }
        if(firstname!=null && !Objects.equals(firstname,user.get().getFirstname())){
            user.get().setFirstname(firstname);
        }

        if(lastname!=null && !Objects.equals(lastname,user.get().getLastname())){
            user.get().setLastname(lastname);
        }
        if(gender!=null && !Objects.equals(gender,user.get().getGender())){
            user.get().setGender(gender);
        }
        if(dateOfBirth!=null && !Objects.equals(dateOfBirth,user.get().getDateOfBirth())){
            user.get().setDateOfBirth(dateOfBirth);
        }
        return true;

    }



    // a function that set the status of citoyen that will be used by admin
    public boolean setStatus(String cin , boolean status){
        Optional<User> user = userRepository.findByCin(cin);
        if(user.isEmpty()) return false;
        user.get().setValid(status);
        return true;
    }
}