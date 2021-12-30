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

    public List<Optional<LikeResDto>> findLike(String googleId) {
        Optional<List<Like>> like = Optional.ofNullable(likeRepository.findAllByGoogleId(googleId));
        List<Optional<Map>> list = new ArrayList<>();
        List<Optional<LikeResDto>> res = new ArrayList<>();
        like.get().stream().forEach((i) -> {
            Optional<Map> map = mapRepository.findById(i.getMapId());
            LikeResDto resDto = LikeResDto.builder()
                    .block(map.get().getContent())
                    .mapCode(map.get().getMapCode())
                    .mapName(map.get().getMapName())
                    .likeId(i.getLikeId())
                    .build();
            res.add(Optional.ofNullable(resDto));
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
