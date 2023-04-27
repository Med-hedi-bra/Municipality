package com.example.mini_projet.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// is used to return the result as a json Object that contains a message attribute
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private String message;
}
