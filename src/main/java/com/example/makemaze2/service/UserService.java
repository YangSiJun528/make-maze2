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
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MapRepository mapRepository;
    private final String local = "C:/Users/user/Downloads/oauth-without-spring-security-master/make-maze2/src/main/resources/static/img";

    public User login(LoginRequestDto loginRequestDto) {
        Optional<User> user = userRepository.findByGoogleId(loginRequestDto.getGoogleId());
        if (user.isEmpty()) {
            User newUser = User.builder()
                    .email(loginRequestDto.getEmail())
                    .googleId(loginRequestDto.getGoogleId())
                    .maps(new ArrayList<Map>())
                    .build();
            return userRepository.save(newUser);
        } else {
            return user.get();
        }
    }

    public MapDto addMap(String googleId, MapDto mapDto, MultipartFile multipartFile) {
        Optional<User> user = userRepository.findByGoogleId(googleId);
        if (!multipartFile.isEmpty()) {
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
                    .img("/img/" + storedFileName)
                    .build();
            mapRepository.save(map);
            mapDto.setImage(map.getImg());
            mapDto.setMapId(map.getMapId());
            return mapDto;
        } else {
            Map map = Map.builder()
                    .content(mapDto.getBlock())
                    .user(user.get())
                    .img("")
                    .build();
            mapRepository.save(map);
            return mapDto;
        }
    }
}
