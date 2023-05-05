package com.example.mini_projet.TEST;


import com.example.mini_projet.Demande.Demande;
import com.example.mini_projet.Demande.DemandeService;
import com.example.mini_projet.Municipality.Municipality;
import com.example.mini_projet.Municipality.MunicipalityService;
import com.example.mini_projet.Statistics.Statistics;
import com.example.mini_projet.Statistics.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("municipality/subadmin")
@PreAuthorize("hasAnyRole('SUBADMIN, ADMIN')")
public class SubAdminController {



    final StatisticsService statisticsService;
    final MunicipalityService municipalityService;
    final DemandeService demandeService;


    @GetMapping("/get")
    public List<Demande> getAllDeamndes(){
        return demandeService.getAll();
    }


    @GetMapping("/municipalities")
    public List<Municipality> getAll(){
        return municipalityService.getAll();
    }



    @PostMapping("/add/municipality/{id}")
    public ResponseEntity<String> addStatistic(@RequestBody Statistics s , @PathVariable("id") Long idMun){

        boolean test = statisticsService.insertStat(s , idMun);
        if(test){
            return ResponseEntity.status(201).body("Statistics added successfully");
        }
        else{
            return ResponseEntity.status(401).body("Failure to add New Statistic ");
        }


    }



}
