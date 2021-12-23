package com.example.makemaze2.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MapDto {
    private String name;
    private String googleId;
    private String block;
//    private String image???;
}
