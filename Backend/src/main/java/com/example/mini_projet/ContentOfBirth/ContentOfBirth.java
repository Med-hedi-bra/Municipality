package com.example.mini_projet.ContentOfBirth;

import com.example.mini_projet.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class ContentOfBirth {
    @Id
    private Long idCob;

    @OneToOne(targetEntity = User.class )
    @JoinColumn(name = "cin")
    private User user;


    private String file;
}
