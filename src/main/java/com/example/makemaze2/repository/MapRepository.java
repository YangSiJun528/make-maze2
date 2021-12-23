package com.example.makemaze2.repository;

import com.example.makemaze2.domain.Map;
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
public interface MapRepository extends JpaRepository<Map,Long> {
    @Query(value = "SELECT m FROM Map m INNER JOIN User u ON m.user.userId = u.userId WHERE u.googleId = :googleId")
    List<Map> findAllByGoogleId(@Param("googleId") String googleId);
}
