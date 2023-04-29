package com.example.mini_projet.Municipality;

import com.example.mini_projet.User.User;
import com.example.mini_projet.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MunicipalityService {
    final MunicipalityRepository municipalityRepository;
    final UserRepository userRepository;

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



    public void addMunicipality(Municipality municipality)
    {
        Optional<Municipality> muni = municipalityRepository.findById(municipality.getId());
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
