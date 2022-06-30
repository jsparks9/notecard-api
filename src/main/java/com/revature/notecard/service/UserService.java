package com.revature.notecard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.Register;
import com.revature.notecard.service.dtos.RegistrationResponse;
import com.revature.notecard.service.exceptions.ResourcePersistenceException;
import com.revature.notecard.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import static com.revature.notecard.service.Encrypt.encrypt;

@Service
@Validated
@Transactional
public class UserService {
    ObjectMapper mapper = new ObjectMapper();
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    public ResponseEntity createUser(@Valid Register regRequestInfo) throws JsonProcessingException {
        User newUser = regRequestInfo.extractUserInfo();

        if( userRepo.existsByUsernameIgnoreCase(newUser.getUsername())) {
            throw new ResourcePersistenceException("There is already a user with that username!");
        }

        newUser.setRole(User.Role.BASIC);
        newUser.setPassword(encrypt(newUser.getPassword()));
        userRepo.save(newUser);
        User storedUser = userRepo.getByUsernameIgnoreCase(newUser.getUsername()).orElseThrow(RuntimeException::new);
        long id = storedUser.getId();

        return ResponseEntity.status(201).body(mapper.writeValueAsString(new RegistrationResponse(storedUser)));

    }
}
