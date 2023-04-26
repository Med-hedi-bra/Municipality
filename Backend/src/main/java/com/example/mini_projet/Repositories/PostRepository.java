package com.example.mini_projet.Repositories;

import com.example.mini_projet.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();

    List<Post> findByMunicipalityId(Long idMun);


//     List<Post> findByMunicipalityId(Long id);
}
