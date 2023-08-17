package com.melis.todoProject.todolist.boundary.controller;

import com.melis.todoProject.todolist.entity.dto.SimpleToDoListModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ToDoListMessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public SimpleToDoListModel greeting(SimpleToDoListModel message) throws Exception {
        Thread.sleep(500); // simulated delay
        return message;
    }

}
