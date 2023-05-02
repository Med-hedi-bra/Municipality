package com.example.mini_projet.Statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    List<Statistics> findAll();

    //Optional<Statistics> getByMunicipalityId(Long id);
}
