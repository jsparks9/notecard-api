package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private static ObjectMapper mapper = new ObjectMapper();

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(path="/all")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(userRepo.findAll()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(); // TODO : custom error + add in Error Aspect
        }
    }



}
