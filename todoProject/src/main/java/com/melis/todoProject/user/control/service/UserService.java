package com.melis.todoProject.user.control.service;


import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import com.melis.todoProject.user.entity.model.UserModel;

public interface UserService {
    public void registerUser(UserModel user);

    public void addListToUser(ToDoListModel toDoListModel, String username);

    public UserModel getUser(String username);

    public void saveUser(UserModel user);

}
