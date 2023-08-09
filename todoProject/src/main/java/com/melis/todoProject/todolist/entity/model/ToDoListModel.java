package com.melis.todoProject.todolist.entity.model;

import com.melis.todoProject.task.entity.model.TaskModel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ToDoList")
public class ToDoListModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String listName;
    private String listDescription;
    @OneToMany
    private ArrayList<TaskModel> listTasks = new ArrayList<TaskModel>();

    public ToDoListModel() {
    }

    public ToDoListModel(String listName, String listDescription) {
        this.listName = listName;
        this.listDescription = listDescription;
    }
}
