package com.example.makemaze2.dto;

import com.example.makemaze2.domain.Map;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MapDto {
    private Long mapId;
    private String block;
    private String image;

    public Map toMap() {
        Map map = new Map();
        Map.builder()
                .content(this.block)
                .build();
        return map;
    }
}
