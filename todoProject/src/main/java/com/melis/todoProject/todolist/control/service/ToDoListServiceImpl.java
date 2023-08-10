package com.melis.todoProject.todolist.control.service;

import com.melis.todoProject.todolist.control.repository.ToDoListRepository;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import com.melis.todoProject.user.control.service.UserService;
import com.melis.todoProject.user.control.service.UserServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    private final ToDoListRepository toDoListRepository;
    private final UserService userService;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository toDoListRepository, UserServiceImp userService) {
        this.toDoListRepository = toDoListRepository;
        this.userService = userService;
    }

    @Override
    public void addNewListToLoggedUser(ToDoListModel toDoListModel, String username) {
        toDoListRepository.save(toDoListModel);
        userService.addListToUser(toDoListModel, username);
    }

    @Transactional
    @Override
    public List<String> getListsFromUser(String username) {
        List<String> list = new ArrayList<>();
        List<ToDoListModel> retrievedLists = userService.getUser(username).getToDoLists();
        retrievedLists.forEach(item -> {
            list.add(item.getListName());
        });
        return list;
    }

    @Transactional
    @Override
    public ToDoListModel getListByName(String name) {
        return toDoListRepository.findByListName(name);
    }
}
