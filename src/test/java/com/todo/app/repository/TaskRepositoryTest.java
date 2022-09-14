package com.todo.app.repository;

import com.todo.app.config.TestApplicationConfig;
import com.todo.app.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = TestApplicationConfig.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    @Order(1)
    void saveTask() {
        Task task = new Task();
        task.setCategory("Work");
        task.setDescription("Test");
        task.setUserId("test-user-id");
        Task savedTask = taskRepository.save(task);
        Assertions.assertNotNull(savedTask);
    }

    @Test
    @Order(2)
    void checkTasksByUserId() {
        Optional<List<Task>> byUserId = taskRepository.findByUserId("test-user-id");
        Assertions.assertTrue(byUserId.isPresent());
        Assertions.assertNotNull(byUserId.get());
    }
}
