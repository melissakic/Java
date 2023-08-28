package com.melis.todoProject.user.control.repository;

import com.melis.todoProject.user.entity.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUsernameNative(String username);
}
