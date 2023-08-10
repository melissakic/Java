package com.melis.todoProject.user.entity.model;


import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "User")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String username;
    private String password;
    @OneToMany
    private List<ToDoListModel> toDoLists = new ArrayList<>();
}
