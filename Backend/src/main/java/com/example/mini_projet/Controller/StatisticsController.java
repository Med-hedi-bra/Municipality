package com.example.mini_projet.Controller;

import com.example.mini_projet.Service.StatisticsService;
import com.example.mini_projet.models.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    final StatisticsService statisticsService;

    @PostMapping("/add/municipality/{id}")
    public ResponseEntity<String> addStatistic(@RequestBody Statistics s , @PathVariable("id") Long idMun){

        boolean test = statisticsService.insert(s , idMun);
      if(test){
         return ResponseEntity.status(201).body("Statistics added successfully");
      }
      else{
          return ResponseEntity.status(401).body("Failure to add New Statistic ");
      }


    }

    @GetMapping("/get")
    public List<Statistics> getAll(){
        return statisticsService.getAll();
    }

    @GetMapping("/get/{id}")
    public Statistics getById(@PathVariable("id") Long id){
         return statisticsService.getById(id);
    }
     @GetMapping("get/municipality/{id}")
     public Statistics getByMunicipalityId(@PathVariable("id") Long idMun){
        return statisticsService.getByIdMunicipality(idMun);
     }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateById(@PathVariable("id") Long id, @RequestBody Statistics s){


        boolean test = statisticsService.updateStatistics(id , s);
        if(test){
           return ResponseEntity.status(200).body("Statistics updated successfully");
        }
        return ResponseEntity.status(400).body("Failure to update Statistics ");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        boolean test =  statisticsService.deleteById(id);
        if(test) return ResponseEntity.status(203).body("Statistics deleted successfully");
        return ResponseEntity.status(400).body("Failure to delete Statistics ");
    }


}
