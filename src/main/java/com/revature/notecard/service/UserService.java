package com.revature.notecard.service;

import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.Register;
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
    public void createUser(@Valid Register regRequestInfo) {
        User newUser = regRequestInfo.extractUserInfo();

        if( userRepo.existsByUsername(newUser.getUsername())) {
            throw new ResourcePersistenceException("There is already a user with that username!");
        }

        newUser.setRole(User.Role.BASIC);
        newUser.setPassword(encrypt(newUser.getPassword()));
        com.revature.notecard.tables.User user = new com.revature.notecard.tables.User(newUser.getUsername(), newUser.getFname(), newUser.getLname(), newUser.getPassword());
        userRepo.save(user);

//        return new UserCreationResponse(user.getId());
    }
}
