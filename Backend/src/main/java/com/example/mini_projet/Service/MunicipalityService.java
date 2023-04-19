package com.example.mini_projet.Service;

import com.example.mini_projet.Repositories.MunicipalityRepository;
import com.example.mini_projet.models.Municipality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MunicipalityService {
    final MunicipalityRepository municipalityRepository;
    public ArrayList<Municipality> getAll(){
        return (ArrayList<Municipality>) municipalityRepository.findAll();
    }

    public Optional<Municipality> getByCodePostale(Long codePostale){
        return municipalityRepository.findById(codePostale);
    }


}
