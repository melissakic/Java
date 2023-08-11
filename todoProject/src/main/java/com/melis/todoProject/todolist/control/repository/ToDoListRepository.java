package com.melis.todoProject.todolist.control.repository;

import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoListModel, Integer> {
    ToDoListModel findByListName(String listName);
    
}
