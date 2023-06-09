package com.example.mini_projet.User;

import com.example.mini_projet.Demande.Demande;
import com.example.mini_projet.ContentOfBirth.ContentOfBirth;
import com.example.mini_projet.Auth.Token;
import com.example.mini_projet.Municipality.Municipality;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter

@Table(name = "user")
public class User implements UserDetails {

    @Autowired
    public static Token token;

    @Id
//    @NotBlank
    @Size(max = 10)
    @Column(name="cin")
    private String cin;

//    @NotBlank
    @Size(max = 50)
    private String firstname;

//    @NotBlank
    @Size(max = 50)
    private String lastname;




//    @NotBlank
    @Size(max = 50)
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;


    private String email;

    //    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private com.example.mini_projet.Enums.Role role;


    // this variable used to enable or disable a user,
    //its value is passed to isEnabled function of the userdetails interface
    // Consider that a disabled user cannot login !
    // please remove the comment to use this feature

    @JsonIgnore
    private boolean status;





    @ManyToOne
    @JoinColumn(name = "id_mun", nullable = true)
    @JsonBackReference
    @Getter
    @Setter

    private Municipality municipality;

    public Integer getMunicipality() {
        return municipality.getIdMun();
    }

    private Integer codeMun ;



    @OneToOne(targetEntity = ContentOfBirth.class , mappedBy = "user")
    private ContentOfBirth contentOfBirth;


    @OneToMany(targetEntity = Demande.class , mappedBy = "user")
    @JsonManagedReference
    private List<Demande> demandes = new ArrayList<>();


//    @OneToMany(targetEntity = File.class, mappedBy = "user",cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<File> cvs = new ArrayList<>();











    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();

    }

    @Override

    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
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
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
        //return status;
        //please remove the comment to use this feature
    }
}
