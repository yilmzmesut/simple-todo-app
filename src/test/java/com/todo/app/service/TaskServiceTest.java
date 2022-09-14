package com.todo.app.service;

import com.todo.app.entity.Task;
import com.todo.app.mapper.TaskMapper;
import com.todo.app.model.dto.TaskDto;
import com.todo.app.model.dto.UserDto;
import com.todo.app.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    @Mock
    UserService userService;

    @Mock
    TaskMapper taskMapper;

    @Test
    void findTasksByUserId() {
        TaskDto taskDto = new TaskDto("task-id", "test task", "test task desc", "work", 1, "test-user-id");
        Task taskEntity = new Task("task-id", "test task", "test task desc", "work", 1, "test-user-id");
        Mockito.when(taskMapper.toDtos(ArgumentMatchers.any()))
                .thenReturn(Collections.singletonList(taskDto));
        Mockito.when(taskRepository.findByUserId(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(Collections.singletonList(taskEntity)));
        Mockito.when(userService.findUsernameFromContext())
                .thenReturn(new UserDto("test-user-id", "Test", "test@test.com", "12345"));
        List<TaskDto> tasksByUserId = taskService.findTasksByUserId();
        Assertions.assertNotNull(tasksByUserId);
        Assertions.assertEquals(1, tasksByUserId.size());
    }
}
