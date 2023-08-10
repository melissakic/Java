package com.melis.todoProject.todolist.entity.model;

import com.melis.todoProject.task.entity.model.TaskModel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
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
    private List<TaskModel> task = new ArrayList<>();
}
