package com.example.mini_projet.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonSerializableSchema

@Getter
@Setter
@Table(name = "municipality")
public class Municipality implements Serializable {


    @Id
    @Column(name="code_mun")
    private Long codeMun;


    @NotBlank
    @Size(max = 50)
    private String secretaire;

    @NotBlank
    @Size(max = 50)
    private String maire;

    @NotBlank
    @Size(max = 50)
    private String president;

    @NotBlank
    @Size(max = 50)
    private String adjoint;


    //@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "municipality")
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
// pas besoin de la relation bidirectionnelle

}
