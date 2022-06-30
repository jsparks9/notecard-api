package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.UserService;
import com.revature.notecard.service.dtos.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RegisterController {

    //Give RegisterController access to UserRepository and UserService class
    private final UserRepository userRepo;
    private final UserService userService;


    // Using @Autowired annotation to have Spring initialize the constructor with the UserRepository
    // and UserService classes as parameters.
    @Autowired
    public RegisterController(UserRepository userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }


    // Setting the Http response status to CREATED with @ResponseStatus
    // Using @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity register(@RequestBody Register registerInfo) throws JsonProcessingException {
         return userService.createUser(registerInfo);
    }


}
