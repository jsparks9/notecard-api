package com.revature.notecard.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.common.dtos.Register;
import com.revature.notecard.tables.users.User;
import com.revature.notecard.tables.users.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ObjectMapper mapper = new ObjectMapper();

    private final UserRepository userRepo;


    public AuthController(@RequestBody Register authRequest, UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/register", consumes = "application/json", produces = "application/json")
    public Register login(@RequestBody Register registerInfo, UserRepository userRepo) {
        return userService.u
    }
}
