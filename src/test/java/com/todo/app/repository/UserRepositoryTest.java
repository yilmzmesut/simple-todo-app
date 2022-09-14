package com.todo.app.repository;

import com.todo.app.config.TestApplicationConfig;
import com.todo.app.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestApplicationConfig.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void saveDummyUser() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setName("Test");
        user.setPassword("12345");
        User savedUser = userRepository.save(user);
        Assertions.assertNotNull(savedUser);
    }

    @Test
    @Order(2)
    void findByEmail() {
        User byEmail = userRepository.findByEmail("test@test.com");
        Assertions.assertNotNull(byEmail);
        Assertions.assertEquals("test@test.com", byEmail.getEmail());
        userRepository.deleteById(byEmail.getId());
    }
}
