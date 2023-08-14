package com.melis.todoProject.task.control.service;

import java.sql.Timestamp;

public class StringToTimestampParser {
    public static Timestamp convertStringToTimestamp(String time) {
        time = time + " 00:00:00.0";
        return Timestamp.valueOf(time);
    }
}
