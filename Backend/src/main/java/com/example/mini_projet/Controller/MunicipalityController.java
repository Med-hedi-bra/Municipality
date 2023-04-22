package com.example.mini_projet.Controller;


import com.example.mini_projet.Service.MunicipalityService;
import com.example.mini_projet.models.Municipality;
import com.example.mini_projet.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/municipality")
@RequiredArgsConstructor
public class MunicipalityController {

    final MunicipalityService municipalityService;


    @GetMapping("/municipalities")
    public ArrayList<Municipality> getAllMuni(){
        return municipalityService.getAll();
    }


    @GetMapping("/{codeMun}")
    public Optional<Municipality> getMunicipalityByCodeMun(@PathVariable("codeMun") Long codeMun)
    {
        return municipalityService.getByCodeMun(codeMun);
    }



    @PostMapping(path = "/add")
    public void addNewMun(@RequestBody Municipality municipality){
        municipalityService.addMunicipality(municipality);
    }


}
