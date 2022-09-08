package com.todo.app.mapper;

import com.todo.app.model.dto.UserDto;
import com.todo.app.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto taskDto);

    UserDto toDto(User task);

    List<User> toEntities(List<UserDto> taskDto);

    List<UserDto> toDtos(List<User> task);
}
