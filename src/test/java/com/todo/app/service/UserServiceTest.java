package com.todo.app.service;

import com.todo.app.entity.User;
import com.todo.app.mapper.UserMapper;
import com.todo.app.model.dto.UserDto;
import com.todo.app.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;


    @Test
    void findByEmail() {
        Mockito.when(userRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(new User("test-user-id", "Test", "test@test.com" , "12345"));
        Mockito.when(userMapper.toDto(ArgumentMatchers.any()))
                .thenReturn(new UserDto("test-user-id", "Test", "test@test.com" , "12345"));
        UserDto byEmail = userService.findByEmail("test@test.com");
        Assertions.assertNotNull(byEmail);
        Assertions.assertEquals(byEmail.getEmail(), "test@test.com");
    }
}
