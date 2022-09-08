package com.todo.app.exception;

public enum TodoAppErrorCode {
    ENTITY_NOT_FOUND("Entity is not found"),
    EXISTING_USER("There is an account with this email."),
    ;

    private final String description;

    TodoAppErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
