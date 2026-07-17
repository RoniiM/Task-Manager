package com.project.TaskManager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest
{
    @NotBlank(message = "Username cannot be blank!")
    @JsonProperty("username")
    private String userName;

    @NotBlank(message = "Password cannot be blank!")
    private String password;
}
