package com.example.mini_projet.Post;

import com.example.mini_projet.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();





//     List<Post> findByMunicipalityId(Long id);
}
