package com.adfa.adfa.service;

import com.adfa.adfa.data.repository.UserRepository;
import com.adfa.adfa.model.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity register (UserEntity user) {
        // register logic
        return user;
    }

    public UserEntity findByUsername (String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
