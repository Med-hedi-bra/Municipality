package com.example.mini_projet.Demande;

import com.example.mini_projet.File.File;
import com.example.mini_projet.Municipality.Municipality;
import com.example.mini_projet.Statistics.Statistics;
import com.example.mini_projet.User.User;
import com.example.mini_projet.Enums.Demande_Type;
import com.example.mini_projet.Enums.State_Enum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
    private Long idDemande;

    @Column
    private String title;


    @Enumerated(EnumType.STRING)
    private Demande_Type type;

    // this enum is not an enum in database is a string
    @Enumerated(EnumType.STRING)
    private  State_Enum state;

//    @Lob
//    private Blob file;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cin")
    @JsonBackReference
    @Getter
    @Setter
    private User user;



    @OneToOne(targetEntity = File.class,mappedBy = "demande" , fetch = FetchType.EAGER)
    @JsonManagedReference
    private File file;





}
