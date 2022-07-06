package com.revature.notecard.receiving;

import com.revature.notecard.service.UserService;
import com.revature.notecard.service.dtos.LoginRequest;
import com.revature.notecard.service.dtos.LoginResponse;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.service.dtos.PrincipalWithToken;
import com.revature.notecard.service.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to check the username and password in a LoginRequest against login information stored on the database.
 * Note that passwords are stored encrypted and the decryption is handled by the UserService
 * If the credentials are valid, this will issue a token to the user for later use in other endpoints.
 */
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
    public PrincipalWithToken authenticate (@RequestBody LoginRequest loginRequest, HttpServletResponse resp) {
        LoginResponse authUser = userService.authenticateUserCredentials(loginRequest);
        Principal prin = new Principal(authUser);
        String token = tokenService.generateToken(prin);
        PrincipalWithToken returnThis = new PrincipalWithToken(prin,token);
        return returnThis;
    }

}
