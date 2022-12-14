package com.todo.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {
    private String id;
    private String name;
    private String description;
    private String category;
    private Integer priority;
    private String userId;
}
