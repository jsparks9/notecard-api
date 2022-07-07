package com.revature.notecard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.*;
import com.revature.notecard.service.exceptions.AuthenticationException;
import com.revature.notecard.service.exceptions.ResourcePersistenceException;
import com.revature.notecard.service.token.TokenService;
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
    private final TokenService tokenService;

    // Using @Autowired annotation to have Spring initialize the constructor with the UserRepository
    // and UserService classes as parameters.
    @Autowired
    public UserService(UserRepository userRepo, TokenService tokenService) {
        this.userRepo = userRepo;
        this.tokenService = tokenService;
    }


    // Helper method for registration endpoint POST request that takes in registration info
    // and saves the new registered user to the UserRepository to be added to the database.
    public ResponseEntity createUser(@Valid Register regRequestInfo) throws JsonProcessingException {
        User newUser = regRequestInfo.extractUserInfo(); //

        if( userRepo.existsByUsernameIgnoreCase(newUser.getUsername())) {
            throw new ResourcePersistenceException("There is already a user with that username!");
        }

        newUser.setRole(User.Role.BASIC);
        newUser.setPassword(encrypt(newUser.getPassword()));
        userRepo.save(newUser);
        User storedUser = userRepo.getByUsernameIgnoreCase(newUser.getUsername()).orElseThrow(RuntimeException::new);
        Principal prin = new Principal(storedUser);
        prin.setAuthUsername(newUser.getUsername());
        String token = tokenService.generateToken(prin);
        PrincipalWithToken returnThis = new PrincipalWithToken(prin,token);
        return ResponseEntity.status(201).body(mapper.writeValueAsString(returnThis));

    }

    /**
     * This method is used by the LoginController to authenticate the entered credentials of a user versus their stored credentials in the database.
     * Note since passwords are stored encrypted we need to use the encrypt method on the entered password so that it can be compared to the stored encrypted value.
     */
    public LoginResponse authenticateUserCredentials(@Valid LoginRequest loginRequest) {
        return userRepo.findUserByUsernameIgnoreCaseAndPassword(loginRequest.getUsername(), encrypt(loginRequest.getPassword()))
                       .map(LoginResponse::new)
                       .orElseThrow(AuthenticationException::new);
    }
}
