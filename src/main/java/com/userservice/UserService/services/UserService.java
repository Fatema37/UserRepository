package com.userservice.UserService.services;

import com.userservice.UserService.dtos.UserRequestDTO;
import com.userservice.UserService.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAllUsers();
    public Optional<User> getUserById(Long id);
    public User createUser(User user);
    public User updateUser(Long id, User userDetails);
    public boolean deleteUser(Long id);


}
