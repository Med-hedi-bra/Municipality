package com.example.mini_projet.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

//@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter

@Table(name = "user")
public class User implements UserDetails, Serializable {

    @Autowired
    public static Token token;

    @Id
    @NotBlank
    @Size(max = 10)
    @Column(name="cin")
    private String cin;

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotBlank
    @Size(max = 50)
    private String lastname;


    @NotBlank
    @Size(max = 50)
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private com.example.mini_projet.enums.Role Role;


    private boolean valid ;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "code_mun")
    @JsonSerialize
    //@JsonBackReference
    private Municipality municipality;

    public Long getMunicipality() {
        return municipality.getCodeMun();
    }
    //    @OneToOne(targetEntity = ContentOfBirth.class , mappedBy = "user")
//    private ContentOfBirth contentOfBirth;
//
//
//    @OneToMany(targetEntity = Demande.class , mappedBy = "user")
//    private Set<Demande> demandes = new HashSet<>();











    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.name()));

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return cin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
