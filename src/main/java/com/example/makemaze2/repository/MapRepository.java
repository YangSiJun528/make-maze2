package com.example.makemaze2.repository;

import com.example.makemaze2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<User,Long> {
}
