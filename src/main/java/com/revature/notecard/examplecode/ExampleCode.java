package com.revature.notecard.examplecode;

/*
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.exceptions.IncorrectPasswordException;
import com.revature.notecard.service.exceptions.ResourceNotFoundException;
import com.revature.notecard.service.dtos.GetUser;
import com.revature.notecard.service.dtos.LoginDetails;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.service.token.JwtConfig;
import com.revature.notecard.service.token.Secured;
import com.revature.notecard.service.token.TokenService;
import com.revature.notecard.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.revature.notecard.service.Encrypt.encrypt;

@RestController
@RequestMapping("/user") //.. /notecard/user
public class ExampleCode {
    private ObjectMapper mapper = new ObjectMapper();
    private UserRepository userRepo;
    private JwtConfig jwtConfig;
    private TokenService service;

    @Autowired
    public ExampleCode(UserRepository userRepo, JwtConfig jwtConfig, TokenService service) {
        this.userRepo = userRepo;
        this.jwtConfig = jwtConfig;
        this.service = service;
    }

/* // GetUser looks like this
@Data
@NoArgsConstructor
public class GetUser {
    private long id;
    private String Username;
    private String fname;
    private String lname;

    public GetUser(User user) {
        this.id = user.getId();
        Username = user.getUsername();
        this.fname = user.getFname();
        this.lname = user.getLname();
    }
}
 */

/*

@Data
@NoArgsConstructor
public class LoginDetails {
    private String username;
    private String password;
}






@Secured(allowedRoles = {"ADMIN"})
@GetMapping(path="/all") //@RequestHeader Map<String, String> allHeaders
public ResponseEntity getAllUsers(@RequestHeader Map<String, String> allHeaders) throws JsonProcessingException {
        System.out.println("Flag0");
        List<GetUser> users = userRepo.findAll().stream().map(GetUser::new).collect(Collectors.toList());
        System.out.println("Flag1");
        return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(users)); // OK = 200
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
*/
