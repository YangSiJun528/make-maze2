package com.example.makemaze2.controller;

import com.example.makemaze2.domain.Map;
import com.example.makemaze2.domain.User;
import com.example.makemaze2.dto.LoginRequestDto;
import com.example.makemaze2.dto.MapDto;
import com.example.makemaze2.service.MapService;
import com.example.makemaze2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@CrossOrigin
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

    @PostMapping(value = "/map/{googleId}")
    public ResponseEntity<MapDto> addMap(
            @PathVariable("googleId") String googleId,
            @RequestPart(value = "map") MapDto mapDto,
            @RequestPart(value = "image", required = false) MultipartFile multipartFile
    ) {
        return ResponseEntity.ok(userService.addMap(googleId, mapDto, multipartFile));
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
    public ResponseEntity<List<Map>> getAllMap() {
        return ResponseEntity.ok(mapService.findAllMap());
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello..!";
    }
}
