package com.todo.app.service;

import com.todo.app.model.dto.TaskDto;
import com.todo.app.mapper.TaskMapper;
import com.todo.app.model.dto.UserDto;
import com.todo.app.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;

    public List<TaskDto> findTasksByUserId() {
        return taskRepository.findByUserId(userService.findUsernameFromContext().getId()).map(taskMapper::toDtos).orElse(null);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public void saveTask(TaskDto taskDto) {
        UserDto user = userService.findUsernameFromContext();
        taskDto.setUserId(user.getId());
        taskRepository.save(taskMapper.toEntity(taskDto));
    }
}
