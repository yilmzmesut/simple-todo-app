package com.todo.app.config;

import com.todo.app.exception.TodoAppException;
import com.todo.app.model.response.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TodoAppException.class)
    public ResponseEntity<Object> handleEntityNotFound(TodoAppException exception, WebRequest request) {
        log.error("EntityNotFoundException : ", exception);
        return ResponseEntity.internalServerError().body(WrapperResponse.builder()
                .errorDetail(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now().toString())
                .build());
    }
}
