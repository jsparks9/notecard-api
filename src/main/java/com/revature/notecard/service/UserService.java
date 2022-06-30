package com.revature.notecard.service;

import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.Register;
import com.revature.notecard.service.dtos.UserCreationResponse;
import com.revature.notecard.service.exceptions.ResourcePersistenceException;
import com.revature.notecard.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import static com.revature.notecard.service.Encrypt.encrypt;

@Service
@Validated
@Transactional
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Validated
    public UserCreationResponse createUser(@Valid Register regRequestInfo) {
        User newUser = regRequestInfo.extractUserInfo();

        if( userRepo.existsByUsernameIgnoreCase(newUser.getUsername())) {
            throw new ResourcePersistenceException("There is already a user with that username!");
        }

        newUser.setRole(User.Role.BASIC);
        newUser.setPassword(encrypt(newUser.getPassword()));
        userRepo.save(newUser);

        return new UserCreationResponse(newUser.getId());
    }
}
