package com.project.TaskManager.security;

import com.project.TaskManager.entity.User;
import com.project.TaskManager.exceptions.NotFoundException;
import com.project.TaskManager.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByuserName(username)
                .orElseThrow(() -> new NotFoundException("User not found."));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
