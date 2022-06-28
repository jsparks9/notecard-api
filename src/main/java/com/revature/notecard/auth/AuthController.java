package com.revature.notecard.auth;

import com.revature.notecard.common.dtos.Register;
import com.revature.notecard.common.dtos.UserCreationResponse;
import com.revature.notecard.tables.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/register", consumes = "application/json", produces = "application/json")
    public UserCreationResponse register(@RequestBody Register registerInfo) {
        return userService.createUser(registerInfo);
    }
}
