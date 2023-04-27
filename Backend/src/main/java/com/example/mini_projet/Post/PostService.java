package com.example.mini_projet.Post;

import com.example.mini_projet.Dto.Request.PostRequest;
import com.example.mini_projet.Municipality.MunicipalityService;
import com.example.mini_projet.Municipality.Municipality;
import com.example.mini_projet.Enums.Post_Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    final PostRepository postRepository;
    final MunicipalityService municipalityService;

    public List<Post> getAll(){
        return postRepository.findAll();
    }

    public Post getById(Long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) return post.get();
        throw new IllegalStateException("Post not found");
    }

    public boolean insert(Post p , Long id_mun){
        Municipality municipality = municipalityService.getById(id_mun);
        try {

             p.setMunicipality(municipality);
            postRepository.save(p);
            return true;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Transactional
    public boolean update(Long id , PostRequest p){
    Optional<Post> post = postRepository.findById(id);
    if(post.isEmpty())return  false;
        String title = p.getTitle();
        String content = p.getContent();
        Post_Type type = p.getType();
        Date date_added = p.getDate_added();

        if(title!=null && !Objects.equals(title , post.get().getTitle())){
            post.get().setTitle(title);
        }
        if(content!=null && !Objects.equals(content , post.get().getContent())){
            post.get().setContent(content);
        }
        if(type!=null && !Objects.equals(type , post.get().getType())){
            post.get().setType(type);
        }
        if(date_added!=null && !Objects.equals(date_added , post.get().getDate_added())){
            post.get().setDate_added(date_added);
        }
        return true;
    }


    public boolean deleteById(Long id){
        try {
            postRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    public boolean deleteAll(){
        try {
            postRepository.deleteAll();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<Post> getByIdMun(Long idMun) {
        return postRepository.findByMunicipalityId(idMun);

    }
}
