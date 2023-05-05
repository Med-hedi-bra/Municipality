package com.example.mini_projet.Statistics;

import com.example.mini_projet.Statistics.StatisticsService;
import com.example.mini_projet.Statistics.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StatisticsController {
    final StatisticsService statisticsService;



    @GetMapping("/get")
    public List<Statistics> getAll(){
        return statisticsService.getAll();
    }

    @GetMapping("/get/{id}")
    public Statistics getById(@PathVariable("id") Long id){
        return statisticsService.getById(id);
    }

    @GetMapping("get/municipality/{id}")
    public Optional<Statistics> getByMunicipalityId(@PathVariable("id") Long idMun){
        return statisticsService.getStatByIdMun(idMun);
    }




    @PostMapping("/add/municipality/{id}")
    public ResponseEntity<String> addStatistic(@RequestBody Statistics statistics , @PathVariable("id") Long idMun)
    {
        boolean test = statisticsService.insertStat(statistics , idMun);

        if(test){
             return ResponseEntity.status(201).body("Statistics added successfully");
        }
        else{
             return ResponseEntity.status(401).body("Failure to add New Statistic ");
        }


    }





    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStatById(@PathVariable("id") Long idStat, @RequestBody Statistics statistics){


        boolean test = statisticsService.updateStatistics(idStat , statistics);
        if(test){
           return ResponseEntity.status(200).body("Statistics updated successfully");
        }
        return ResponseEntity.status(400).body("Failure to update Statistics ");
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long idStat){
        boolean test =  statisticsService.deleteById(idStat);
        if(test) return ResponseEntity.status(203).body("Statistics deleted successfully");
        return ResponseEntity.status(400).body("Failure to delete Statistics ");
    }


}
