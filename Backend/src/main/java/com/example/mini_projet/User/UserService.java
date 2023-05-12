package com.example.mini_projet.User;

import com.example.mini_projet.Dto.Request.UserUpdateRequest;
import com.example.mini_projet.Municipality.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserService {


    final UserRepository userRepository;
    final MunicipalityRepository municipalityRepository;

    @Autowired

    public UserService(UserRepository userRepository,MunicipalityRepository municipalityRepository){
        this.userRepository = userRepository;
        this.municipalityRepository = municipalityRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public User getByCin(String userCin){
        Optional<User> user = userRepository.findByCin(userCin);
        if(user.isPresent()) return user.get();
        throw new IllegalStateException("User not found");
    }

    public List<User> getUsersByCodeMun(@PathVariable Integer codeMun){
        List<User> users = userRepository.findAll();
        List<User> usersByMun =
                users.stream().filter(usr -> usr.getMunicipality().equals(municipalityRepository.findById(codeMun).get()))
                        .collect(Collectors.toList());
        return usersByMun;
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

    @Transactional
    public boolean update(String cin , UserUpdateRequest userUpdateRequest)  {
         User user  = userRepository.findByCin(cin).get();
         if(user==null) return false;

         if(userUpdateRequest.getEmail()!=null) user.setEmail(userUpdateRequest.getEmail());
         if(userUpdateRequest.getLastname()!=null) user.setLastname(userUpdateRequest.getLastname());
         if(userUpdateRequest.getFirstname()!=null) user.setFirstname(userUpdateRequest.getFirstname());
         if(userUpdateRequest.getGender()!=null) user.setGender(userUpdateRequest.getGender());
         if(userUpdateRequest.getDateOfBirth()!=null) user.setDateOfBirth(userUpdateRequest.getDateOfBirth());
//         if(userUpdateRequest.getRole()!=null) user.setRole(userUpdateRequest.getRole());
//         if(userUpdateRequest.getPassword()!=null) user.setPassword(userUpdateRequest.getPassword());
         return true;



    }

    // a function that set the status of citoyen that will be used by admin
//    public boolean setStatus(String cin , boolean status){
//        Optional<User> user = userRepository.findByCin(cin);
//        if(user.isEmpty()) return false;
//        user.get().setValid(status);
//        return true;
//    }
}
