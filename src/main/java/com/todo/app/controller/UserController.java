package com.todo.app.controller;

import com.todo.app.model.dto.UserDto;
import com.todo.app.model.request.SignInRequest;
import com.todo.app.model.request.SignUpRequest;
import com.todo.app.model.response.SignInResponse;
import com.todo.app.model.response.WrapperResponse;
import com.todo.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public WrapperResponse<UserDto> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        UserDto user = userService.signUp(signUpRequest);
        return new WrapperResponse<>(user, HttpStatus.CREATED.value());
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public WrapperResponse<SignInResponse> signIn(@RequestBody SignInRequest request) {
        SignInResponse tokenResponse = userService.signIn(request);
        return new WrapperResponse<>(tokenResponse, HttpStatus.OK.value());
    }
}
