package com.example.test;

import java.io.Serializable;

public class NotEnoughTicketsException extends Exception implements Serializable {
    private String message;

    public NotEnoughTicketsException() {
    }

    public NotEnoughTicketsException(String s) {
        super(s);
        message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
