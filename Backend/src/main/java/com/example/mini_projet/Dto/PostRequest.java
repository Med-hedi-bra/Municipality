package com.example.mini_projet.Dto;

import com.example.mini_projet.models.enums.Post_Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {

    private Long id;

    private String title;

    private String content;

    private Post_Type type;

    private Date date_added;

}
