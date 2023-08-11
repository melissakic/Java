package com.melis.todoProject.todolist.entity.model;

import com.melis.todoProject.task.entity.model.TaskModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ToDoList")
public class ToDoListModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String listName;
    private String listDescription;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskModel> task = new ArrayList<>();


}
