package com.example.makemaze2.service;

import com.example.makemaze2.domain.Map;
import com.example.makemaze2.domain.User;
import com.example.makemaze2.dto.LoginRequestDto;
import com.example.makemaze2.dto.MapDto;
import com.example.makemaze2.repository.MapRepository;
import com.example.makemaze2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

}
