package com.melis.todoProject.user.control.repository;

import com.melis.todoProject.user.entity.dto.SimpleUserModel;
import com.melis.todoProject.user.entity.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUsernameNative(String username);

    @Query(nativeQuery = true)
    List<SimpleUserModel> findAllUsernames();
}
