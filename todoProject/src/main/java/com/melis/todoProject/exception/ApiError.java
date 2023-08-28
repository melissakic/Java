package com.melis.todoProject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ApiError {

    public enum ErrorCode {
        NOT_FOUND(101),
        USER_NOT_OWNER(102);
        private final int code;

        ErrorCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    private ErrorCode code;
    private String description;
}
