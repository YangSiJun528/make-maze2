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
    private String mapName;
    private String mapCode;
    private String block;
}
