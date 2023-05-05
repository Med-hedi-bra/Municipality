package com.example.mini_projet.Municipality;

import com.example.mini_projet.Post.Post;
import com.example.mini_projet.Statistics.Statistics;
import com.example.mini_projet.User.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "municipality")
public class Municipality {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mun")
    private Long idMun;

    //@NotBlank
    @Size(min = 3)
    private String secritaire;

    //@NotBlank
    @Size(min = 3)
    private String maitre;

    //@NotBlank
    @Size(min = 3)
    private String president;


    @OneToMany(mappedBy = "municipality")
    @Getter
    @Setter
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }



    @OneToOne(targetEntity = Statistics.class,mappedBy = "municipality" , fetch = FetchType.EAGER)
    @JsonManagedReference
    private Statistics statistics;


    @OneToMany(targetEntity = Post.class , mappedBy = "municipality" ,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Post> posts;



}
