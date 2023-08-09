package com.melis.todo.task.control.service;


import com.melis.todo.task.control.repository.TaskRepository;
import com.melis.todo.task.entity.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskModel addTask(TaskModel task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(TaskModel task) {
        taskRepository.delete(task);
    }

    @Override
    public TaskModel setAsFinished(TaskModel task) {
        return null;
    }
}
