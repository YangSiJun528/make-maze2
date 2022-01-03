package com.example.makemaze2.service;

import com.example.makemaze2.domain.Map;
import com.example.makemaze2.domain.User;
import com.example.makemaze2.dto.LoginRequestDto;
import com.example.makemaze2.dto.MapDto;
import com.example.makemaze2.repository.MapRepository;
import com.example.makemaze2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MapRepository mapRepository;
    private final String local = "C:/Users/user/Downloads/oauth-without-spring-security-master/make-maze2/src/main/resources/static/img";
    Random random = new Random();

    public User login(LoginRequestDto loginRequestDto) {
        Optional<User> user = userRepository.findByGoogleId(loginRequestDto.getGoogleId());
        if (user.isEmpty()) {
            User newUser = User.builder()
                    .email(loginRequestDto.getEmail())
                    .googleId(loginRequestDto.getGoogleId())
                    .img(loginRequestDto.getImg())
                    .name(loginRequestDto.getName())
                    .maps(new ArrayList<Map>())
                    .build();
            return userRepository.save(newUser);
        } else {
            return user.get();
        }
    }

    public String randomCode() {
        String generatedString = random.ints(48,122 + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(12)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public boolean isValidaRandomCode(String code) {
        Map map = mapRepository.findByMapCode(code);
        return map == null ? true : false;
    }
/*
    public MapDto addMap(String googleId, MapDto mapDto, MultipartFile multipartFile) {
        Optional<User> user = userRepository.findByGoogleId(googleId);
        Optional<MultipartFile> image = Optional.ofNullable(multipartFile);
        String code;
        do {
            code = randomCode();
        } while (!isValidaRandomCode(code));
        if (image.isPresent()) {
            String originalFile = multipartFile.getOriginalFilename();
            String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
            String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
            File saveFile = new File(local, storedFileName);
            try {
                multipartFile.transferTo(saveFile);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            Map map = Map.builder()
                    .content(mapDto.getBlock())
                    .user(user.get())
                    .mapCode(code)
                    .mapName(mapDto.getMapName())
                    .img("/img/" + storedFileName)
                    .build();
            mapRepository.save(map);
            mapDto.setImage(map.getImg());
            mapDto.setMapCode(map.getMapCode());
            mapDto.setMapId(map.getMapId());
            return mapDto;
        } else {
            Map map = Map.builder()
                    .content(mapDto.getBlock())
                    .user(user.get())
                    .mapCode(code)
                    .mapName(mapDto.getMapName())
                    .img("")
                    .build();
            mapRepository.save(map);
            mapDto.setImage(map.getImg());
            mapDto.setMapCode(map.getMapCode());
            mapDto.setMapId(map.getMapId());
            return mapDto;
        }
    }
*/
    public MapDto addMap(String googleId, MapDto mapDto) {
        Optional<User> user = userRepository.findByGoogleId(googleId);
        String code;
        do {
            code = randomCode();
        } while (!isValidaRandomCode(code));
        Map map = Map.builder()
                .content(mapDto.getBlock())
                .user(user.get())
                .mapCode(code)
                .mapName(mapDto.getMapName())
                .userName(user.get().getName())
                .img(user.get().getImg())
                .build();
        mapRepository.save(map);
        mapDto.setMapCode(map.getMapCode());
        mapDto.setMapId(map.getMapId());
        mapDto.setImg(user.get().getImg());
        mapDto.setUserName(user.get().getName());
        return mapDto;
    }

    public User loginByEmail(LoginRequestDto loginRequestDto) {
        Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());
        if (user.isEmpty()) {
            return null;
        } else {
            return user.get();
        }
    }
}
