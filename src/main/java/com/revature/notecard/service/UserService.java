package com.revature.notecard.service;

import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.NewUserRequest;
import com.revature.notecard.service.dtos.ResourceCreationResponse;
import com.revature.notecard.service.dtos.UserResponse;
import com.revature.notecard.service.exceptions.ResourceNotFoundException;
import com.revature.notecard.service.exceptions.ResourcePersistenceException;
import com.revature.notecard.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserResponse> fetchAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public List<UserResponse> fetchUsersByRole(User.Role role) {
        return userRepo.findUsersByRole(role.toString())
                .stream()
                .map(UserResponse::new).
                collect(Collectors.toList());
    }

    public UserResponse fetchUserById(long id) {
        return userRepo.findById(id)
                .map(UserResponse::new)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public UserResponse fetchUserByUsername(@Email String username) {
        return userRepo.findByUsernameIgnoreCase(username)
                .map(UserResponse::new)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public ResourceCreationResponse createUser(@Valid NewUserRequest newUserRequest) {

        User newUser = newUserRequest.extractResource();

        if (userRepo.existsByUsernameIgnoreCase(newUser.getUsername())) {
            throw new ResourcePersistenceException("There is already a user with that username!");
        }

        newUser.setRole(User.Role.BASIC);
        userRepo.save(newUser);
        return new ResourceCreationResponse(newUser.getId());
    }

    
}
