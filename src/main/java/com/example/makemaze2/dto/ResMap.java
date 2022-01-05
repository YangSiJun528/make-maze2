package com.example.makemaze2.dto;

import lombok.*;

import javax.persistence.Column;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResMap {
    private Long mapId;
    private ArrayList content;
    private String mapName;
    private String mapCode;
    private String img;
    private String userName;
    private String userGoogleId;
}
