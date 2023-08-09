package com.melis.todo.task.control.repository;

import com.melis.todo.task.entity.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
}
