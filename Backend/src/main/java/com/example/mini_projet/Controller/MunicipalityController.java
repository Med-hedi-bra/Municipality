package com.example.mini_projet.Controller;


import com.example.mini_projet.Service.MunicipalityService;
import com.example.mini_projet.models.Municipality;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipality")
@RequiredArgsConstructor
public class MunicipalityController {
    final MunicipalityService municipalityService;
    @GetMapping("/")
    public List<Municipality> getAll(){
        return municipalityService.getAll();
    }
    @GetMapping("/{id}")
    public Municipality getById(@PathVariable("id") Long id){
        return municipalityService.getById(id);
    }
    @PostMapping("/")
    public ResponseEntity<String> addNewMunicipality(@RequestBody Municipality municipality){
        boolean test = municipalityService.insert(municipality);
        if(test) return ResponseEntity.status(201).body("Municipality added successfully");
        return ResponseEntity.status(402).body("Failure to add Municipality");
    }

}
