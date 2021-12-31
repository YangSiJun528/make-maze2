package com.example.makemaze2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeResDto {
    private Long likeId;
    private Long mapId;
    private String mapName;
    private String mapCode;
    private String block;
}
