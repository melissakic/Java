package com.melis.todoProject.exception;

import com.melis.todoProject.exception.customExceptions.ListNotFoundException;
import com.melis.todoProject.exception.customExceptions.TaskNotFoundException;
import com.melis.todoProject.exception.customExceptions.UserNotOwnerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotOwnerException.class)
    protected ResponseEntity<Object> handleUserNotOwner(UserNotOwnerException exception) {
        ApiError apiError = new ApiError(ApiError.ErrorCode.USER_NOT_OWNER, exception.getMessage());
        return buildResponseEntity(apiError, HttpStatus.FORBIDDEN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ListNotFoundException.class)
    protected ResponseEntity<Object> handleListNotFound(ListNotFoundException exception) {
        ApiError apiError = new ApiError(ApiError.ErrorCode.NOT_FOUND, exception.getMessage());
        return buildResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TaskNotFoundException.class)
    protected ResponseEntity<Object> handleTaskNotFound(TaskNotFoundException exception) {
        ApiError apiError = new ApiError(ApiError.ErrorCode.NOT_FOUND, exception.getMessage());
        return buildResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus status) {
        return new ResponseEntity<>(apiError, status);
    }
}
