package com.melis.todoProject.user.entity.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
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
//    @OneToMany
//    private ArrayList<ToDoListModel> toDoLists;

    public UserModel() {
    }

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
