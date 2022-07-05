package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.UserService;
import com.revature.notecard.service.dtos.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Annotating RestController class for inversion of control
@RequestMapping("/auth")  // Setting RegisterController request mapping to '/auth'
public class RegisterController {

    //Give RegisterController access to UserRepository and UserService class
    private final UserRepository userRepo;
    private final UserService userService;


    // Using @Autowired annotation to have Spring initialize the constructor with the UserRepository
    // and UserService interface and class as parameters.
    @Autowired
    public RegisterController(UserRepository userRepo, UserService userService) {
        this.userRepo = userRepo; // Setting constructors params, so I can make use of them in the register method
        this.userService = userService; // Setting constructors params, so I can make use of them in the register method
    }


    @ResponseStatus(HttpStatus.CREATED)  // Setting the Http response status to CREATED with @ResponseStatus
    @PostMapping(path="/register", consumes = "application/json", produces = "application/json")  // Using @PostMapping to set the request path, the request body type, and response type
    public ResponseEntity register(@RequestBody Register registerInfo) throws JsonProcessingException {  // Creating a register method that uses @RequestBody to use the request body data as a parameter of type Register and returns type ResponseEntity
         return userService.createUser(registerInfo); // Returns a call to createUser from the UserService class, passing in registerInfo and adding that user to the database
    }


}
