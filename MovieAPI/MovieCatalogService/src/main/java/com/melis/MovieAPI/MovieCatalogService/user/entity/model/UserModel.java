package com.melis.MovieAPI.MovieCatalogService.user.entity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;

@Data
@Entity
@Table(name="User")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userId;
    @ElementCollection
    protected HashMap<Integer,Double> ratings;
}
