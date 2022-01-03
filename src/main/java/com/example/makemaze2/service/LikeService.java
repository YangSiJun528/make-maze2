package com.example.makemaze2.service;

import com.example.makemaze2.domain.Like;
import com.example.makemaze2.domain.Map;
import com.example.makemaze2.domain.User;
import com.example.makemaze2.dto.LikeResDto;
import com.example.makemaze2.repository.LikeRepository;
import com.example.makemaze2.repository.MapRepository;
import com.example.makemaze2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final MapRepository mapRepository;

    public Optional<List<LikeResDto>> findLike(String googleId) {
        Optional<List<Like>> like = Optional.ofNullable(likeRepository.findAllByGoogleId(googleId));
        Optional<List<LikeResDto>> res = Optional.of(new ArrayList());
        like.get().stream().forEach((i) -> {
            Optional<Map> map = mapRepository.findById(i.getMapId());
            LikeResDto resDto = LikeResDto.builder()
                    .mapId(map.get().getMapId())
                    .block(map.get().getContent())
                    .mapCode(map.get().getMapCode())
                    .mapName(map.get().getMapName())
                    .userName(map.get().getUserName())
                    .img(map.get().getImg())
                    .likeId(i.getLikeId())
                    .build();
            res.get().add(resDto);
        });
        return res;
    }

    public Like addLike(String googleId, Long mapId) {
        Optional<User> user = userRepository.findByGoogleId(googleId);
        Optional<Map> map = mapRepository.findById(mapId);
        Like like = Like.builder()
                .user(user.get())
                .mapId(map.get().getMapId())
                .build();
        likeRepository.save(like);
        return like;
    }

    public Like deleteLike(Long likeId) {
        Optional<Like> like = likeRepository.findById(likeId);
        likeRepository.delete(like.get());
        return like.get();
    }

}
