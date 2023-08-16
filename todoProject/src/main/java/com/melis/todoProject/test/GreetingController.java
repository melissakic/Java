package com.melis.todoProject.test;

import com.melis.todoProject.todolist.entity.dto.SimpleToDoListModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public SimpleToDoListModel greeting(SimpleToDoListModel toDoList) throws Exception {
        return toDoList;
    }

}
