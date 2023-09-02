package com.melis.MovieAPI.MovieCatalogService.user.control.repository;

import com.melis.MovieAPI.MovieCatalogService.user.entity.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
