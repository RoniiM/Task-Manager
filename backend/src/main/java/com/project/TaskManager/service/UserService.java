package com.project.TaskManager.service;

import com.project.TaskManager.dto.Response;
import com.project.TaskManager.dto.UserRequest;
import com.project.TaskManager.entity.User;

public interface UserService
{
    Response<?> signUp(UserRequest userRequest);

    Response<?> login(UserRequest userRequest);

    User getCurrentLoggedInUser();
}
