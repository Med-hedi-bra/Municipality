package com.example.mini_projet.Demande;

import com.example.mini_projet.Dto.Request.DemandeRequest;
import com.example.mini_projet.Dto.Response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demande")
public class DemandeController {

    final DemandeService demandeService;

    @GetMapping("/get")
    public List<Demande> getAll(){
        return demandeService.getAll();
    }
    @GetMapping("/get/{id}")
    public Demande getById(@PathVariable("id") Long id){
        return demandeService.getById(id);
    }

    @GetMapping("/get/user/{cin}")
    public List<Demande> getByUserCin(@PathVariable("cin") String cin){
        return demandeService.getByUserCin(cin);
    }

    @PostMapping(value = "/add/user/{cin}")
    public ResponseEntity addDemande(@ModelAttribute DemandeRequest demandeRequest , @PathVariable("cin") String userCin
    ){
        boolean test = demandeService.insert(demandeRequest , userCin);
        MessageResponse response;
        if(test){
            response = new MessageResponse("Demande added successfully");
            return new ResponseEntity(response , HttpStatusCode.valueOf(201));
        }
        response = new MessageResponse("Failure to add Demande ");
        return new ResponseEntity(response , HttpStatusCode.valueOf(410));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update( @RequestBody DemandeRequest demandeRequest , @PathVariable("id") Long id){
        boolean test = demandeService.update(demandeRequest , id);
        MessageResponse response;
        if(test){
            response = new MessageResponse("Demande updated  successfully");
            return new ResponseEntity(response , HttpStatusCode.valueOf(201));
        }
        response = new MessageResponse("Failure to update Demande ");
        return new ResponseEntity(response , HttpStatusCode.valueOf(410));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAll(){
        MessageResponse response = new MessageResponse();
        boolean test = demandeService.deleteAll();
        if(test){
            response.setMessage("Deleted all Demands");
            return new ResponseEntity(response , HttpStatusCode.valueOf(210));
        }
        response.setMessage("Failure to delete all demands");
        return new ResponseEntity(response , HttpStatusCode.valueOf(450));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteByID(@PathVariable("id") Long id ){
        MessageResponse response = new MessageResponse();
        boolean test = demandeService.delete(id);
        if(test){
            response.setMessage("Deleted  Demand successfully");
            return new ResponseEntity(response , HttpStatusCode.valueOf(210));
        }
        response.setMessage("Failure to delete  demand");
        return new ResponseEntity(response , HttpStatusCode.valueOf(450));
    }
}
