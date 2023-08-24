package com.melis.todoProject.todolist.control.service;

import com.melis.todoProject.todolist.entity.dto.SimpleToDoListModel;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;

import java.util.List;

public interface ToDoListService {
    void addNewListToLoggedUser(ToDoListModel toDoListModel, String username);

    List<String> getListNamesFromUser(String username);

    List<ToDoListModel> getListFromUser(String username);

    List<SimpleToDoListModel> getSimpleListsFromUser(String username);

    ToDoListModel getListByName(String name);

    ToDoListModel getListById(Integer id);

    boolean checkToDoListAuthorisation(String username, Integer listId);

    boolean toDoListIsNotValid(String username, Integer listId);

    void deleteList(Integer listId, String username);
}
