package com.melis.todoProject.task.boundary.controller;

import com.melis.todoProject.task.entity.model.TaskModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TaskMessageController {

    @MessageMapping("/add")
    @SendTo("/list/message")
    public List<TaskModel> greeting(List<TaskModel> message) {
        return message;
    }
}
