package com.melis.todoProject.todolist.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleToDoListModel {
    private String listName;
    private String listDescription;
}
