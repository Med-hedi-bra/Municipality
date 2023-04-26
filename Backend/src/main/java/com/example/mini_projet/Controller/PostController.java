package com.example.mini_projet.Controller;

import com.example.mini_projet.Dto.PostRequest;
import com.example.mini_projet.Service.MunicipalityService;
import com.example.mini_projet.Service.PostService;
import com.example.mini_projet.models.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
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
    public List<Post> getByIdMunicipality(@PathVariable("id") Long id){
        return  postService.getByIdMun(id);

    }

    @PostMapping("/add/municipality/{id}")
    public ResponseEntity<String> addNewPost(@PathVariable("id") Long id_mun,@RequestBody Post p){

        boolean test = postService.insert(p , id_mun);
        if(test) return ResponseEntity.status(201).body("Post added successfully");
        return  ResponseEntity.status(405).body("Failure to add Post");
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
