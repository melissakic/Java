package com.melis.todoProject.task.entity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "task")
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String taskName;
    private Timestamp taskEndTime;
    private boolean done = false;
}
