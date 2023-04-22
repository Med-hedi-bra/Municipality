package com.example.mini_projet.Repositories;

import com.example.mini_projet.models.Municipality;
import com.example.mini_projet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality,Long> {

    Optional<Municipality> findByCodeMun(Long codemun);

    List<Municipality> findAll();

    @Query("SELECT m FROM Municipality m  WHERE m.codeMun = ?1")
    Municipality findByCodeMuni(Long codemun);


}
