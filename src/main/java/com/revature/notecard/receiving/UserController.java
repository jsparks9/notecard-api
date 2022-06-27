package com.revature.notecard.receiving;

import com.revature.notecard.repos.CardRepository;
import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.UserResponse;
import com.revature.notecard.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/user")
public class UserController {
    private static ObjectMapper mapper = new ObjectMapper();
    private final String name = this.getClass().getName();
    private final UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping

    // TODO : protect with token access for ADMIN role only
    @GetMapping(path="/all")
    public ResponseEntity getUsers() throws JsonProcessingException {
        List<User> users = userRepo.findAll();
        List<UserResponse> resp = new Stack<UserResponse>();
        for (User user : users) {
            resp.add(new UserResponse(user));
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(resp));
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(userRepo.findAll()));
//        } catch (JsonProcessingException e) {
//            HttpHeaders responseHeaders = new HttpHeaders();
//            //.setLocation(location);
//            responseHeaders.set("MyResponseHeader", "MyValue");
//            return new ResponseEntity<String>("Internal Error", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR); // 500
//        }
    }
}
