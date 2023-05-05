package com.example.mini_projet.Demande;

import com.example.mini_projet.Dto.Request.DemandeRequest;
import com.example.mini_projet.Dto.Response.MessageResponse;
import com.example.mini_projet.File.File;
import com.example.mini_projet.File.FileService;
import com.example.mini_projet.File.message.ResponseFile;
import com.example.mini_projet.File.message.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demande")
public class DemandeController {

    final DemandeService demandeService;
    final FileService fileService;

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



    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
//            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//            System.out.println(request.getUserPrincipal().getName());
//            System.out.println(request.getContentType());
            fileService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @PostMapping(value = "/add/user/{cin}")
    public ResponseEntity<ResponseMessage> addDemandeAndFile(
            @RequestBody DemandeRequest demandeRequest,
                                                      @PathVariable("cin") String userCin,
                                                      @RequestParam("file") MultipartFile file) throws IOException {
        String message = "";
        try{
            demandeService.insert(demandeRequest , userCin);
            fileService.store(file);
            message = "Demande and Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        }
        catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = fileService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getIdFile())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getFileType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        File fileDB = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
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



    // il faut toujours laisser de trace
//    @DeleteMapping("/deleteAll")
//    public ResponseEntity deleteAll(){
//        MessageResponse response = new MessageResponse();
//        boolean test = demandeService.deleteAll();
//        if(test){
//            response.setMessage("Deleted all Demands");
//            return new ResponseEntity(response , HttpStatusCode.valueOf(210));
//        }
//        response.setMessage("Failure to delete all demands");
//        return new ResponseEntity(response , HttpStatusCode.valueOf(450));
//    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteByID(@PathVariable("id") Long idDemande ){
        MessageResponse response = new MessageResponse();
        boolean test = demandeService.delete(idDemande);
        if(test){
            response.setMessage("Deleted  Demand successfully");
            return new ResponseEntity(response , HttpStatusCode.valueOf(210));
        }
        response.setMessage("Failure to delete  demand");
        return new ResponseEntity(response , HttpStatusCode.valueOf(450));
    }
}
