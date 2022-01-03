package com.example.makemaze2.repository;

import com.example.makemaze2.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByGoogleId(String googleId);
    Optional<User> findByEmail(String googleId);

    @Override
    List<User> findAll();
}
