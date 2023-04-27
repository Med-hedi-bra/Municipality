package com.example.mini_projet.Repositories;

import com.example.mini_projet.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    List<Statistics> findAll();
    Statistics getByMunicipalityId(Long id);
}
