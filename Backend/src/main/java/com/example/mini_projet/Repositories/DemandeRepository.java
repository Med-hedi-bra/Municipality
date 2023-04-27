package com.example.mini_projet.Repositories;

import com.example.mini_projet.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande , Long> {
    List<Demande> findAll();
    List<Demande> findByUserCin(String cinUser);
}
