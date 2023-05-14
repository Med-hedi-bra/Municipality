package com.example.mini_projet.Demande;


import com.example.mini_projet.Dto.Request.DemandeRequest;
import com.example.mini_projet.File.FileRepository;
import com.example.mini_projet.User.UserService;
import com.example.mini_projet.User.User;
import com.example.mini_projet.Enums.State_Enum;
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
    final FileRepository fileRepository;




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
                    .title(demandeRequest.getTitle())
                    .state(State_Enum.PENDING)
                    .type(demandeRequest.getType())
                    .user(user)
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


    @Transactional
    public boolean update(DemandeRequest demandeRequest, Long id)
    {
        try{
            Demande demande = getById(id);
            if(demandeRequest.getType() != null ){
                demande.setType(demandeRequest.getType());
            }
            if(demandeRequest.getState() != null ){
                demande.setState(demandeRequest.getState());
            }
<<<<<<< HEAD
            if(demandeRequest.getTitle() !=null) demande.setTitle(demandeRequest.getTitle());
=======
            if(demandeRequest.getTitle()!=null) demande.setTitle(demandeRequest.getTitle());
>>>>>>> 918a0f0240494f209da519d2462b4543783c45ef
            return true;
        }
        catch (Exception e){
            return false;
        }



    }


    public boolean deleteAll(){
        try {
            demandeRepository.deleteAll();
            return true;
        }
        catch (Exception e){
            return  false;
        }
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


}
