package com.example.makemaze2.repository;

import com.example.makemaze2.domain.Like;
import com.example.makemaze2.domain.Map;
import com.example.makemaze2.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    @Query(value = "SELECT l FROM Like l INNER JOIN User u ON l.user.userId = u.userId WHERE u.googleId = :googleId")
    List<Like> findAllByGoogleId(@Param("googleId") String googleId);
}
