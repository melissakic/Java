package com.melis.MovieAPI.MovieRatingService.user.control.repository;

import com.melis.MovieAPI.MovieRatingService.user.entity.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUserId(Integer userId);
}
