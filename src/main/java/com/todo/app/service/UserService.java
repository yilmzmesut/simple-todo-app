package com.todo.app.service;

import com.todo.app.entity.User;
import com.todo.app.exception.TodoAppErrorCode;
import com.todo.app.exception.TodoAppException;
import com.todo.app.mapper.UserMapper;
import com.todo.app.model.dto.UserDto;
import com.todo.app.model.request.SignInRequest;
import com.todo.app.model.request.SignUpRequest;
import com.todo.app.model.response.SignInResponse;
import com.todo.app.repository.UserRepository;
import com.todo.app.security.UserDetail;
import com.todo.app.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserDto findByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email));
    }

    public UserDto signUp(SignUpRequest signUpRequest) {
        UserDto user = findByEmail(signUpRequest.getEmail());
        if (user != null) {
            throw new TodoAppException(TodoAppErrorCode.EXISTING_USER, TodoAppErrorCode.EXISTING_USER.getDescription() + " email = " + user.getEmail());
        }
        User savedUser = userRepository.save(User.builder()
                .email(signUpRequest.getEmail())
                .name(signUpRequest.getName())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build());
        return userMapper.toDto(savedUser);
    }

    public SignInResponse signIn(SignInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return SignInResponse.builder()
                .accessToken(jwt)
                .tokenType("Bearer")
                .expiresIn(jwtUtils.getExpirationDateFromToken(jwt).getTime())
                .build();
    }

    public UserDto findUsernameFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return findByEmail(userDetail.getUsername());
    }
}
