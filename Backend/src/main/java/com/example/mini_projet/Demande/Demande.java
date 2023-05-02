package com.example.mini_projet.Demande;

import com.example.mini_projet.User.User;
import com.example.mini_projet.Enums.Demande_Type;
import com.example.mini_projet.Enums.State_Enum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demande")
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_dem")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Demande_Type type;

    // this enum is not an enum in database is a string
    @Enumerated(EnumType.STRING)
    private  State_Enum state;

    @Lob
    private Blob file;


    @ManyToOne(targetEntity = User.class ,fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "cin")
    @JsonManagedReference
    private User user;


}
