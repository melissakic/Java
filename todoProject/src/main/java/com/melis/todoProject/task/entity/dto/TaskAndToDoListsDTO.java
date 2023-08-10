package com.melis.todoProject.task.entity.dto;

import com.melis.todoProject.task.entity.model.TaskModel;
import lombok.Data;

import java.util.List;

@Data
public class TaskAndToDoListsDTO {
    private List<String> lists;
    private TaskModel taskModel;
    private String selectedList;
    private String selectedEndTime;
}
