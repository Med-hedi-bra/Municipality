package com.example.mini_projet.Service;

import com.example.mini_projet.Repositories.MunicipalityRepository;
import com.example.mini_projet.models.Municipality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MunicipalityService {
    final MunicipalityRepository municipalityRepository;

    public  Municipality getById(Long id) {
        Optional<Municipality> municipality = municipalityRepository.findById(id);
        if(municipality.isEmpty()) throw new IllegalStateException("Municipliaty inexistant");
        return municipality.get();
    }

    public List<Municipality> getAll(){
        return  municipalityRepository.findAll();
    }

    public Municipality getByCodePostale(Long codePostale){
        Optional<Municipality> municipality= municipalityRepository.findById(codePostale);
        if(municipality.isPresent()) return municipality.get();
        else throw new IllegalStateException("Municipality inexistant");
    }


    public boolean insert(Municipality municipality) {
        try{
            municipalityRepository.save(municipality);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }




}
