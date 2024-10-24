package com.userservice.UserService.services;

import com.userservice.UserService.exceptions.UserNotFoundException;
import com.userservice.UserService.exceptions.UserServiceException;
import com.userservice.UserService.models.User;
import com.userservice.UserService.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Transactional
    public User createUser(User user) {
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            throw new UserServiceException("Error creating user: " + e.getMessage());
        }
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        try {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setActive(userDetails.isActive());
            return userRepo.save(user);
        } catch (Exception e) {
            throw new UserServiceException("Error updating user: " + e.getMessage());
        }
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        try {
            userRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new UserServiceException("Error deleting user: " + e.getMessage());
        }
    }
}
