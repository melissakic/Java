package com.melis.todoProject.task.control.service;


import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;
import com.melis.todoProject.task.entity.model.TaskModel;

public interface TaskService {
    void addTask(TaskAndToDoListsDTO task);

    TaskModel getTaskById(Integer id);

    void setTaskToDone(Integer taskId);

    void deleteTask(Integer taskId, String username);

    boolean checkTaskAuthorisation(String username, Integer taskId);

    boolean checkTaskNotExists(Integer id);

    void editTask(TaskModel task);

    void changeStatus(Integer taskId);

    void scheduleFetchingUnfinishedTasks();

    void scheduleDeletingFinishedTasks();
}
