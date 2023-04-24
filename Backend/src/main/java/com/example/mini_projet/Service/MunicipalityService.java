package com.example.mini_projet.Service;

import com.example.mini_projet.Repositories.MunicipalityRepository;
import com.example.mini_projet.Repositories.UserRepository;
import com.example.mini_projet.models.Municipality;
import com.example.mini_projet.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MunicipalityService {
    final MunicipalityRepository municipalityRepository;
    final UserRepository userRepository;



    public ArrayList<Municipality> getAll()
    {
        return (ArrayList<Municipality>) municipalityRepository.findAll();
    }

    public Optional<Municipality> getByCodeMun(Long codeMun)
    {
        return municipalityRepository.findByCodeMun(codeMun);
    }


    public void addMunicipality(Municipality municipality)
    {
        Optional<Municipality> muni = municipalityRepository.findByCodeMun(municipality.getCodeMun());
        if (muni.isPresent()){
            throw new IllegalStateException("la municipalite existe !");
        }
        municipalityRepository.save(municipality);
    }

    @Transactional
    public Municipality addUserToMunicipality(
            Long codeMun,
            String cin){
        User user = userRepository.findById(cin).get();
        Municipality municipality = municipalityRepository.findById(codeMun).get();
        user.setMunicipality(municipality);
        municipality.addUser(user);
        return municipalityRepository.save(municipality);

    }


}
