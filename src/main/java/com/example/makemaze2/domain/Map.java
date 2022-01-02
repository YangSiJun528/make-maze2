package com.example.makemaze2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "map")
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Long mapId;

    @Column(name = "content", length = 21845)
    private String content;

    @Column(name = "mapName")
    private String mapName;

    @Column(name = "mapCode")
    private String mapCode;

    @Column(name = "img")
    private String img;

    @Column(name = "userName")
    private String userName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
