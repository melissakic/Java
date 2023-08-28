package com.melis.todoProject.todolist.control.service;

import com.melis.todoProject.task.entity.model.TaskModel;
import com.melis.todoProject.todolist.control.repository.ToDoListRepository;
import com.melis.todoProject.todolist.entity.dto.SimpleToDoListModel;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import com.melis.todoProject.user.control.service.UserService;
import com.melis.todoProject.user.control.service.UserServiceImp;
import com.melis.todoProject.user.entity.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
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
        UserModel user = userService.getUser(username);
        user.getToDoLists().add(toDoListModel);
    }

    @Override
    public List<String> getListNamesFromUser(String username) {
        List<String> list = new ArrayList<>();
        List<ToDoListModel> retrievedLists = userService.getUser(username).getToDoLists();
        retrievedLists.forEach(item -> list.add(item.getListName()));
        return list;
    }

    @Override
    public ToDoListModel getListByName(String name) {
        return toDoListRepository.findByListName(name);
    }

    @Override
    public List<ToDoListModel> getListFromUser(String username) {
        return new ArrayList<>(userService.getUser(username).getToDoLists());
    }

    @Override
    public ToDoListModel getListById(Integer id) {
        Optional<ToDoListModel> fetched = toDoListRepository.findById(id);
        ToDoListModel data = new ToDoListModel();
        if (fetched.isEmpty()) return null;
        data.setId(fetched.get().getId());
        for (TaskModel task : fetched.get().getTask()) {
            data.getTask().add(task);
        }
        return data;
    }

    @Override
    public boolean checkToDoListAuthorisation(String username, Integer listId) {
        UserModel user = userService.getUser(username);
        for (ToDoListModel list : user.getToDoLists()) {
            if (list.getId().equals(listId)) return false;
        }
        return true;
    }

    @Override
    public boolean checkToDoListNotExists(Integer listId) {
        ToDoListModel list = getListById(listId);
        return list == null;
    }

    @Override
    public void deleteList(Integer listId, String username) {
        UserModel user = userService.getUser(username);
        user.getToDoLists().removeIf(item -> item.getId().equals(listId));
        toDoListRepository.deleteById(listId);
    }

    @Override
    public List<SimpleToDoListModel> getSimpleListsFromUser(String username) {
        List<ToDoListModel> lists = getListFromUser(username);
        List<SimpleToDoListModel> simpleLists = new ArrayList<>();
        for (ToDoListModel list : lists) {
            simpleLists.add(new SimpleToDoListModel(list.getListName(), list.getListDescription()));
        }
        return simpleLists;
    }
}
