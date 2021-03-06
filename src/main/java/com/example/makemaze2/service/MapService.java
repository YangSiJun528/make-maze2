package com.example.makemaze2.service;

import com.example.makemaze2.domain.Map;
import com.example.makemaze2.dto.ResMap;
import com.example.makemaze2.repository.MapRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

//    public Map addMap(String googleId, MapDto mapDto, MultipartFile multipartFile) {
//        Optional<MultipartFile> img = Optional.ofNullable(multipartFile);
//
//        if(!img.isPresent()) {  // 만약 Null 일때
//
//        } else {
//
//        }
//
//        return new Map();
//    }

    public List<Map> findMap(String googleId) {
        Optional<List<Map>> map = Optional.ofNullable(mapRepository.findAllByGoogleId(googleId));
        return map.get();
    }

    public Map globalDeleteMap(Long mapId) {
        Optional<Map> map = mapRepository.findById(mapId);
        mapRepository.deleteById(map.get().getMapId());
        return map.get();
    }

    public Map deleteMap(Long mapId, String googleId) {
        Optional<Map> map = mapRepository.findById(mapId);
        List<Map> userMaps = mapRepository.findAllByGoogleId(googleId);
        userMaps.stream().filter(i -> i.getMapId() == mapId);
        if (userMaps == null) {
            mapRepository.deleteById(map.get().getMapId());
        }
        return map.get();
    }

    public List<Map> findAllMap() {
        Optional<List<Map>> map = Optional.ofNullable(mapRepository.findAll());
    return map.get();
    }

    public ResMap findMapA(String mapId) throws JsonProcessingException {
        Optional<Map> map = mapRepository.findById(Long.valueOf(mapId));
        ArrayList<String> list = (ArrayList<String>) new ObjectMapper().readValue(map.get().getContent(), List.class);
        ResMap resMap = ResMap.builder()
                .content(list)
                .img(map.get().getImg())
                .mapCode(map.get().getMapCode())
                .mapId(map.get().getMapId())
                .mapName(map.get().getMapName())
                .userGoogleId(map.get().getUserGoogleId())
                .userName(map.get().getUserName())
                .build();
        return resMap;
    }
}

