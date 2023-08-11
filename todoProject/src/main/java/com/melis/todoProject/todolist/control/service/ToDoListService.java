package com.melis.todoProject.todolist.control.service;

import com.melis.todoProject.todolist.entity.model.ToDoListModel;

import java.util.List;

public interface ToDoListService {
    public void addNewListToLoggedUser(ToDoListModel toDoListModel, String username);

    public List<String> getListNamesFromUser(String username);

    public List<ToDoListModel> getListFromUser(String username);

    public ToDoListModel getListByName(String name);

    public ToDoListModel getListById(Integer id);

    public boolean checkIfUserCanAcces(String username, Integer listId);

    public void deleteList(Integer listId, String username);

}
