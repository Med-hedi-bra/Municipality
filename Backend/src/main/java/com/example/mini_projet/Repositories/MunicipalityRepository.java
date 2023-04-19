package com.example.mini_projet.Repositories;


import com.example.mini_projet.models.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality , Long> {
}
