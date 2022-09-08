package com.todo.app.mapper;

import com.todo.app.model.dto.TaskDto;
import com.todo.app.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskDto taskDto);

    TaskDto toDto(Task task);

    List<Task> toEntities(List<TaskDto> taskDto);

    List<TaskDto> toDtos(List<Task> task);

}
