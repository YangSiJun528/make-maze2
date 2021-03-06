package com.example.makemaze2.controller;

import com.example.makemaze2.domain.Like;
import com.example.makemaze2.domain.Map;
import com.example.makemaze2.domain.User;
import com.example.makemaze2.dto.LikeResDto;
import com.example.makemaze2.dto.LoginRequestDto;
import com.example.makemaze2.dto.MapDto;
import com.example.makemaze2.dto.ResMap;
import com.example.makemaze2.service.LikeService;
import com.example.makemaze2.service.MapService;
import com.example.makemaze2.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MapService mapService;
    private final LikeService likeService;

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }

    @PostMapping("/loginByEmail")
    public ResponseEntity<User> loginByEmail(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        User user = userService.loginByEmail(loginRequestDto);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

/*
    @PostMapping(value = "/map/{googleId}")
    public ResponseEntity<MapDto> addMap(
            @PathVariable("googleId") String googleId,
            @RequestPart(value = "map") MapDto mapDto,
            @RequestPart(value = "image", required = false) MultipartFile multipartFile
    ) {
        return ResponseEntity.ok(userService.addMap(googleId, mapDto, multipartFile));
    }
*/
    @PostMapping(value = "/map/{googleId}")
    public ResponseEntity<MapDto> addMap(
            @PathVariable("googleId") String googleId,
            @RequestBody MapDto mapDto
    ) {
        return ResponseEntity.ok(userService.addMap(googleId, mapDto));
    }

    @GetMapping("/map/{googleId}")
    public ResponseEntity<List<Map>> getMap(
            @PathVariable("googleId") String googleId
    ) {
        return ResponseEntity.ok(mapService.findMap(googleId));
    }

    @GetMapping("/map1/{mapId}")
    public ResponseEntity<ResMap> getMapA(
            @PathVariable("mapId") String mapId
    ) throws JsonProcessingException {
        return ResponseEntity.ok(mapService.findMapA(mapId));
    }

    @DeleteMapping("/map/{mapId}")
    public ResponseEntity<Map> globalDeleteMap(
            @PathVariable("mapId") Long mapId
    ) {
        return ResponseEntity.ok(mapService.globalDeleteMap(mapId));
    }

    //?????? ?????? ?????? ?????? ?????? ????????? ??????
    @DeleteMapping("/map/{googleId}/{mapId}")
    public ResponseEntity<Map> deleteLike(
            @PathVariable("mapId") Long mapId,
            @PathVariable("googleId") String googleId
    ) {
        return ResponseEntity.ok(mapService.deleteMap(mapId, googleId));
    }

    @GetMapping("/map")
    public ResponseEntity<List<Map>> getAllMap() {
        return ResponseEntity.ok(mapService.findAllMap());
    }

    @GetMapping("/hello")
    public String hi() {
        return "hello...!";
    }

    @GetMapping(value = "/like/{googleId}/{mapId}")
    public ResponseEntity<Like> addMap(
            @PathVariable("googleId") String googleId,
            @PathVariable("mapId") Long mapId
    ) {
        return ResponseEntity.ok(likeService.addLike(googleId, mapId));
    }

    @GetMapping("/like/{googleId}")
    public ResponseEntity<Optional<List<LikeResDto>>> getLike(
            @PathVariable("googleId") String googleId
    ) {
        return ResponseEntity.ok(likeService.findLike(googleId));
    }

    @DeleteMapping("/like/{likeId}")
    public ResponseEntity<Like> deleteLike(
            @PathVariable("likeId") Long likeId
    ) {
        return ResponseEntity.ok(likeService.deleteLike(likeId));
    }

}


