package com.example.mini_projet.Demande;

import com.example.mini_projet.Demande.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande , Long> {
    List<Demande> findAll();
    List<Demande> findByUserCin(String cinUser);
}
