package com.example.mini_projet.Municipality;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipality")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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



    @PostMapping(path = "/add")
    public void addNewMun(@RequestBody Municipality municipality){
        municipalityService.addMunicipality(municipality);
    }

}
