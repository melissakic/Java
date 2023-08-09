package com.melis.todoProject.task.control.repository;

import com.melis.todoProject.task.entity.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
}
