package com.example.mini_projet.Service;


import com.example.mini_projet.Dto.Request.DemandeRequest;
import com.example.mini_projet.Repositories.DemandeRepository;
import com.example.mini_projet.models.Demande;
import com.example.mini_projet.models.User;
import com.example.mini_projet.models.enums.State_Enum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.serial.SerialBlob;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemandeService {
    final DemandeRepository demandeRepository;
    final UserService userService;
    public List<Demande> getAll(){
        return demandeRepository.findAll();
    }

    public Demande getById(Long id){
        Optional<Demande> demande = demandeRepository.findById(id);
        if(demande.isPresent()) return demande.get();
        throw new IllegalStateException("Demande d'existe pas");
    }

    public List<Demande> getByUserCin(String userCin){
        return demandeRepository.findByUserCin(userCin);
    }

    public boolean insert(DemandeRequest demandeRequest , String userCin){

        try {
            User user = userService.getByCin(userCin);
            Demande demande = Demande.builder()
                    .state(State_Enum.PENDING)
                    .type(demandeRequest.getType())
                    .user(user)
                    .file(new SerialBlob(demandeRequest.getFile().getBytes()))
                    .build();
            demandeRepository.save(demande);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
//    public List<Demande> getByMunicipality(Long idMun){
//
//    }


    public boolean deleteAll(){
        try {
            demandeRepository.deleteAll();
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
    public boolean deleteAllByUser(Long userCin){
        return true;
    }
    public boolean delete(Long id){

        try {
            demandeRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    @Transactional
    public boolean update(DemandeRequest demandeRequest, Long id) {
        try{
            Demande demande = getById(id);
            if(demandeRequest.getType() != null && !Objects.equals(demandeRequest.getType() , demande.getType())){
                demande.setType(demandeRequest.getType());
            }
            if(demandeRequest.getState() != null && !Objects.equals(demandeRequest.getState()  , demande.getState())){
                demande.setState(demandeRequest.getState());
            }
            return true;
        }
        catch (Exception e){
            return false;
        }



    }
}
