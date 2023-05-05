package com.example.mini_projet.Post;

import com.example.mini_projet.Dto.Request.PostRequest;
import com.example.mini_projet.Dto.Response.MessageResponse;
import com.example.mini_projet.Municipality.MunicipalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostController {
    final PostService postService;
    final MunicipalityService municipalityService;

    @GetMapping("/get")
    public List<Post> getAll(){
        return postService.getAll();
    }

    @GetMapping("/get/{id}")
    public  Post getById(@PathVariable("id") Long id){
        return postService.getById(id);
    }

    @GetMapping("/get/municipality/{id}")
    public List<Post> getByIdMunicipality(@PathVariable("id") Long idMun){

        return postService.getPostsByIdMun(idMun);


    }


    @PostMapping("/add/municipality/{id}")
    public ResponseEntity addNewPost(@PathVariable("id") Long id_mun, @RequestBody Post p){
        MessageResponse response;
        boolean test = postService.insert(p , id_mun);
        if(test){
             response = new MessageResponse("Post added successfully");
            return new ResponseEntity(response , HttpStatus.resolve(202));
        }
         response = new MessageResponse("Post Not added successfully");
        return  new ResponseEntity(response , HttpStatus.resolve(404));
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        boolean test = postService.deleteById(id);
        if(test) return ResponseEntity.status(202).body("Delete one post successfully");
        return ResponseEntity.status(410).body("failure to delete one post");
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        boolean test = postService.deleteAll();
        if(test) return ResponseEntity.status(202).body("Deleted all posts successfully");
        return  ResponseEntity.status(410).body("failure to delete all");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id , @RequestBody PostRequest post){
        boolean test = postService.update(id,post);
        if(test) return ResponseEntity.status(202).body("Post updated successfully");
        return ResponseEntity.status(405).body("Failure to update Post");
    }

}
