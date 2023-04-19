package com.example.mini_projet.Repositories;

import com.example.mini_projet.models.Municipality;
import com.example.mini_projet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MunicipalityRepository extends JpaRepository<Municipality,String> {

    Optional<Municipality> findByCodeMun(String codeMun);


}
