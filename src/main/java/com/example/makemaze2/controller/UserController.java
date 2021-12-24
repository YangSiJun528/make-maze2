package com.example.makemaze2.controller;

import com.example.makemaze2.domain.Map;
import com.example.makemaze2.domain.User;
import com.example.makemaze2.dto.LoginRequestDto;
import com.example.makemaze2.dto.MapDto;
import com.example.makemaze2.service.MapService;
import com.example.makemaze2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MapService mapService;

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }

    @PostMapping("/map/{googleId}")
    public ResponseEntity<Map> addMap(
            @PathVariable("googleId") String googleId,
            @RequestPart(value = "map") MapDto mapDto,
            @RequestPart(value = "img", required = false) MultipartFile multipartFile
    ) {

        return ResponseEntity.ok(new Map());
    }

    @GetMapping("/map/{googleId}")
    public ResponseEntity<List<Map>> getMap(
            @PathVariable("googleId") String googleId
    ) {
        return ResponseEntity.ok(mapService.findMap(googleId));
    }

    @DeleteMapping("/map/{mapId}")
    public ResponseEntity<Map> deleteMap(
            @PathVariable("mapId") Long mapId
    ) {
        return ResponseEntity.ok(mapService.deleteMap(mapId));
    }

    @GetMapping("/map")
    public ResponseEntity<MapDto> getAllMap() {
        return ResponseEntity.ok(new MapDto());
    }
}
