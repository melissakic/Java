package com.melis.todoProject.user.entity.model;


import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import com.melis.todoProject.user.entity.dto.SimpleUserModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NamedNativeQuery(
        name = "UserModel.findByUsernameNative",
        query = "SELECT * FROM User WHERE username = :username",
        resultClass = UserModel.class
)
@NamedNativeQuery(name = "UserModel.findAllUsernames", query = "SELECT username FROM User", resultSetMapping = "SimpleUserModelMapping")
@SqlResultSetMapping(name = "SimpleUserModelMapping", classes = @ConstructorResult(
        targetClass = SimpleUserModel.class,
        columns = {
                @ColumnResult(name = "username", type = String.class)
        }
))
@Table(name = "User")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer id;
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDoListModel> toDoLists = new ArrayList<>();
}
