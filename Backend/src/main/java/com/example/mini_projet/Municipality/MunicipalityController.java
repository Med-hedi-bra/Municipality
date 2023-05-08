package com.example.mini_projet.Municipality;


import com.example.mini_projet.Dto.Request.MunicipalityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Municipality> getById(@PathVariable("id") Integer id){
        return municipalityService.getById(id);
    }



    @PostMapping(path = "/add")
    public void addNewMun(@RequestBody Municipality municipality){
        municipalityService.addMunicipality(municipality);
    }

    @PutMapping("update/{id}")
    public  ResponseEntity updateMun(@PathVariable Integer id, @RequestBody MunicipalityRequest municipalityRequest){
        boolean test = municipalityService.update(id,municipalityRequest);
        if(test) return ResponseEntity.status(200).body("Mun updated with sucess");
        else return  ResponseEntity.status(407).body("failure to uodate mun");
    }
}
