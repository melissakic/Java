package com.melis.todoProject.task.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="task")
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String taskName;
    private Timestamp taskEndTime;
    private boolean done=false;

    public TaskModel(){}

    public TaskModel(String taskName, Timestamp taskEndTime,boolean done){
        this.taskName=taskName;
        this.taskEndTime=taskEndTime;
        this.done=done;
    }

}
