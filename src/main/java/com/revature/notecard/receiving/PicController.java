package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.PicRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.PicUrlOnly;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.service.token.JwtConfig;
import com.revature.notecard.service.token.TokenService;
import com.revature.notecard.tables.ProfilePicURL;
import com.revature.notecard.service.exceptions.AuthenticationException;
import com.revature.notecard.tables.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pic")
public class PicController {
    private UserRepository userRepo;
    private PicRepository picRepo;
    private ObjectMapper mapper = new ObjectMapper();
    private JwtConfig jwtConfig;
    private TokenService service;

    public PicController(UserRepository userRepo, PicRepository picRepo, ObjectMapper mapper, JwtConfig jwtConfig, TokenService service) {
        this.userRepo = userRepo;
        this.picRepo = picRepo;
        this.mapper = mapper;
        this.jwtConfig = jwtConfig;
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity getPic(@RequestHeader(value = "Authorization", required = false) String tokenMaybe) throws JsonProcessingException {
        Principal prin = service.extractTokenDetails(tokenMaybe);
        long userId = prin.getAuthUserId();
        User user = userRepo.findById(userId).orElseThrow(AuthenticationException::new); // just checks if the user exists
        ProfilePicURL picRaw = picRepo.findById(userId).orElseThrow(RuntimeException::new);
        PicUrlOnly pic = new PicUrlOnly(picRaw);
        return ResponseEntity.status(200).body(mapper.writeValueAsString(pic));
    }

    @Transactional
    @Modifying
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void postPic(@RequestHeader(value = "Authorization", required = false) String tokenMaybe, @RequestBody PicUrlOnly picObj) {
        Principal prin = service.extractTokenDetails(tokenMaybe);
        long userId = prin.getAuthUserId();
        User user = userRepo.findById(userId).orElseThrow(AuthenticationException::new); // just checks if the user exists
        String pic = picObj.getPic();

        if(picRepo.existsById(userId)) {
            throw new RuntimeException(); // changing pic not supported!

        }
        picRepo.save(new ProfilePicURL(userId, pic));

    }

}
