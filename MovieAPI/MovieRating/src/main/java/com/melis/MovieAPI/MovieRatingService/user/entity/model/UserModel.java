package com.melis.MovieAPI.MovieRatingService.user.entity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "User")
@Data
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private String username;
    @ElementCollection
    @CollectionTable(name = "user_ratings", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyColumn(name = "rating_key")
    @Column(name = "rating_value")
    private Map<String, Double> ratings = new HashMap<>();
}
