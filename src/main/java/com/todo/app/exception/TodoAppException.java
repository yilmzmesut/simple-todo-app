package com.todo.app.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class TodoAppException extends RuntimeException {

    private final TodoAppErrorCode errorCode;

    public TodoAppException(TodoAppErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public TodoAppException(TodoAppErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public TodoAppException(String message, Throwable cause, TodoAppErrorCode errorCode) {
        super(errorCode.getDescription(), cause);
        this.errorCode = errorCode;
    }

    public TodoAppException(Throwable cause, TodoAppErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
