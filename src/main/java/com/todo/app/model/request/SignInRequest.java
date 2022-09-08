package com.todo.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequest {
    @NotNull(message = "email cannot be null")
    private String email;
    @NotNull(message = "password cannot be null")
    private String password;
}
