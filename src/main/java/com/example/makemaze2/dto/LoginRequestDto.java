package com.example.makemaze2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @JsonProperty(value="googleId")
    private String googleId;

    @JsonProperty(value="email")
    private String email;

    @JsonProperty(value="name")
    private String name;

    @JsonProperty(value="img")
    private String img;
}
