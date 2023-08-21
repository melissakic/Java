package com.melis.todoProject.task.control.service;

import org.springframework.context.ApplicationEvent;

public class DeleteFinishedTaskEvent extends ApplicationEvent {
    public DeleteFinishedTaskEvent(Object source) {
        super(source);
    }
}
