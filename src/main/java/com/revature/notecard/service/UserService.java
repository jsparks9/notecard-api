package com.revature.notecard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.LoginRequest;
import com.revature.notecard.service.dtos.LoginResponse;
import com.revature.notecard.service.dtos.Register;
import com.revature.notecard.service.dtos.RegistrationResponse;
import com.revature.notecard.service.exceptions.AuthenticationException;
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
    //Give UserService class access to UserRepository and a new ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    private final UserRepository userRepo;

    // Using @Autowired annotation to have Spring initialize the constructor with the UserRepository
    // and UserService classes as parameters.
    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    // Helper method for registration endpoint POST request.
    public ResponseEntity createUser(@Valid Register regRequestInfo) throws JsonProcessingException {
        User newUser = regRequestInfo.extractUserInfo(); //

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

    public LoginResponse authenticateUserCredentials(@Valid LoginRequest loginRequest) {
        return userRepo.findUserByUsernameIgnoreCaseAndPassword(loginRequest.getUsername(), encrypt(loginRequest.getPassword()))
                       .map(LoginResponse::new)
                       .orElseThrow(AuthenticationException::new);
    }
}
