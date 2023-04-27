package com.example.mini_projet.Dto.Request;

import com.example.mini_projet.Enums.Post_Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor




public class PostRequest {


    private String title;

    private String content;

    private Post_Type type;

    private Date date_added;

}
