package com.example.makemaze2.service;

import com.example.makemaze2.domain.Map;
import com.example.makemaze2.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

//    public Map addMap(MapDto mapDto) {
//        Map newMap = Map.builder()
//
//        return new Map();
//    }

    public List<Map> findMap(String googleId) {
        Optional<List<Map>> map = Optional.ofNullable(mapRepository.findAllByGoogleId(googleId));
        return map.get();
    }

    public Map deleteMap(Long mapId) {
        Optional<Map> map = mapRepository.findById(mapId);
        mapRepository.deleteById(map.get().getMapId());
        return map.get();
    }

    public List<Map> findAllMap(String googleId) {
        Optional<List<Map>> map = Optional.ofNullable(mapRepository.findAll());
        return map.get();
    }
}
