package com.example.makemaze2.controller;

import com.example.makemaze2.dto.LoginResponsetDto;
import com.example.makemaze2.dto.MapDto;
import com.example.makemaze2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponsetDto> login() {
        return ResponseEntity.ok(new LoginResponsetDto());
    }

    @PostMapping("/map/{googleId}")
    public ResponseEntity<ArrayList<MapDto>> addMap(
            @PathVariable("googleId") String googleId,
            @RequestBody MapDto mapdto
    ) {
        return ResponseEntity.ok(new ArrayList<MapDto>());
    }

    @GetMapping("/map/{googleId}")
    public ResponseEntity<MapDto> getMap(
            @PathVariable("googleId") String googleId
    ) {
        return ResponseEntity.ok(new MapDto());
    }

    @DeleteMapping("/map/{googleId}/{mapId}")
    public ResponseEntity<MapDto> deleteMap(
            @PathVariable("googleId") String googleId,
            @PathVariable("mapId") String mapId
    ) {
        return ResponseEntity.ok(new MapDto());
    }
}
