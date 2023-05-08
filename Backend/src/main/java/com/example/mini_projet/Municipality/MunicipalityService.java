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






    public List<Municipality> getAll(){
        return  municipalityRepository.findAll();
    }


    public Optional<Municipality> getById(Integer idMun) {
        Optional<Municipality> municipality = municipalityRepository.findById(idMun);
        if(municipality.isEmpty()) throw new IllegalStateException("Municipliaty inexistant");
        return municipality;
    }








    public void addMunicipality(Municipality municipality)
    {
        Optional<Municipality> muni = municipalityRepository.findById(municipality.getIdMun());
        if (muni.isPresent()){
            throw new IllegalStateException("la municipalite existe !");
        }
        municipalityRepository.save(municipality);
    }


    @Transactional
    public Municipality addUserToMunicipality(
            Integer codeMun,
            String cin){
        User user = userRepository.findById(cin).get();
        Municipality municipality = municipalityRepository.findByCodeMuni(codeMun);
        user.setMunicipality(municipality);
        municipality.addUser(user);
        return municipalityRepository.save(municipality);

    }




}
