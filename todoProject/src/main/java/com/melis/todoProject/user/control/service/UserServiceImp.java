package com.melis.todoProject.user.control.service;


import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import com.melis.todoProject.user.control.repository.UserRepository;
import com.melis.todoProject.user.entity.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addListToUser(ToDoListModel toDoListModel, String username) {
        UserModel user = userRepository.findByUsername(username);
        user.getToDoLists().add(toDoListModel);
    }

    @Override
    @Transactional
    public UserModel getUser(String username) {
        UserModel user = userRepository.findByUsername(username);
        return user;
    }
}
