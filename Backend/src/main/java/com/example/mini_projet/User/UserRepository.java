package com.example.mini_projet.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findByCin(String cin);
    List<User> findAll();




    //List<User> getUsersBy

    //@Query("SELECT u FROM  User u  WHERE u. = ?1")
    //List<User> getUserByCodeMun(Long codeMun);

}
