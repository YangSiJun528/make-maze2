package com.example.makemaze2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "img")
    private String img;

    @JsonBackReference
    @OneToMany(mappedBy = "mapId")
    private List<Map> maps = new ArrayList<Map>();

    @JsonBackReference
    @OneToMany(mappedBy = "likeId")
    private List<Like> likes = new ArrayList<Like>();
}