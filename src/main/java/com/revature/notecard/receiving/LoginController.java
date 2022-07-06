package com.revature.notecard.receiving;

import com.revature.notecard.service.UserService;
import com.revature.notecard.service.dtos.LoginRequest;
import com.revature.notecard.service.dtos.LoginResponse;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.service.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UserService userService;

    private final TokenService tokenService;

    public LoginController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public Principal authenticate (@RequestBody LoginRequest loginRequest, HttpServletResponse resp) {
        LoginResponse authUser = userService.authenticateUserCredentials(loginRequest);
        Principal payload = new Principal(authUser);
        String token = tokenService.generateToken(payload);
        resp.setHeader("Authorization", token);
        return payload;
    }

}
