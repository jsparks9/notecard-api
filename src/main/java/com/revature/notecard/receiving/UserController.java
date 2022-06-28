package com.revature.notecard.receiving;

import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.Exceptions.IncorrectPasswordException;
import com.revature.notecard.service.Exceptions.ResourceNotFoundException;
import com.revature.notecard.service.auth.JwtConfig;
import com.revature.notecard.service.auth.TokenService;
import com.revature.notecard.service.dtos.LoginDetails;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.revature.notecard.service.Encrypt.encrypt;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepo;
    private JwtConfig jwtConfig;
    private TokenService service;

    @Autowired
    public UserController(UserRepository userRepo, JwtConfig jwtConfig, TokenService service) {
        this.userRepo = userRepo;
        this.jwtConfig = jwtConfig;
        this.service = service;
    }

    @PostMapping(path="/login")
    public ResponseEntity login(@RequestBody LoginDetails details, HttpServletRequest req) {
        String tokenMaybe = req.getHeader("Authorization");
        Principal prin;
        if (tokenMaybe != null) {
            try {
                prin = service.extractTokenDetails(tokenMaybe);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (prin != null) {
                // user is already logged in
                // Should they be logged in again or ... ?
                // maybe offered a logout link?
            }
        }
        // for logging the user in
        User user = userRepo.findByUsernameIgnoreCase(details.getUsername()).orElseThrow(ResourceNotFoundException::new);
        if (!user.getPassword().equals(encrypt(details.getPassword()))) throw new IncorrectPasswordException();
        String token = service.generateToken(new Principal(user));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return new ResponseEntity<String>("Login Successful", responseHeaders, HttpStatus.OK); // 200
    }

}
