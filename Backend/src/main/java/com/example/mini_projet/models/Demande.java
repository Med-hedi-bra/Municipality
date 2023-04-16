package com.example.mini_projet.models;

import com.example.mini_projet.models.enums.Demande_Type;
import com.example.mini_projet.models.enums.State_Enum;
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
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Demande_Type type;

    @Enumerated(EnumType.STRING)
    private  State_Enum state;

    @Lob
    private Blob file;


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "cin")
    private User user;


}
