package com.example.mini_projet.Municipality;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality , Integer> {



    Optional<Municipality> findById(Integer idMun);



    List<Municipality> findAll();



    @Query("SELECT m FROM Municipality m  WHERE m.idMun = ?1")
    Municipality findByCodeMuni(Integer idMun);


}
