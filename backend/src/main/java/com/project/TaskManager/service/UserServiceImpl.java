package com.project.TaskManager.service;

import com.project.TaskManager.dto.Response;
import com.project.TaskManager.dto.UserRequest;
import com.project.TaskManager.entity.User;
import com.project.TaskManager.enums.Role;
import com.project.TaskManager.exceptions.BadRequestException;
import com.project.TaskManager.exceptions.NotFoundException;
import com.project.TaskManager.repo.UserRepo;
import com.project.TaskManager.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService
{

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public Response<?> signUp(UserRequest userRequest) {
        Optional<User> existingUser = userRepo.findByuserName(userRequest.getUserName());
        if(existingUser.isPresent())
        {
            throw new BadRequestException("Username already taken.");
        }

        User user = User.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(Role.USER)
                .userName(userRequest.getUserName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();

        userRepo.save(user);
        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Account created successfully.")
                .build();
    }

    @Override
    public Response<?> login(UserRequest userRequest) {
        User user = userRepo.findByuserName(userRequest.getUserName())
                .orElseThrow(() -> new NotFoundException("Username not found."));

        if(!passwordEncoder.matches(userRequest.getPassword(), user.getPassword()))
        {
            throw new BadRequestException("Wrong password.");
        }

        String token = jwtUtils.generateToken(user.getUserName());
        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .data(token)
                .build();
    }

    @Override
    public User getCurrentLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByuserName(username).orElseThrow(() -> new NotFoundException("User not found."));
    }
}
