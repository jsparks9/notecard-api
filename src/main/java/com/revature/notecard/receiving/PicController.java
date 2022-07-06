package com.revature.notecard.receiving;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.PicRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.PicUrlOnly;
import com.revature.notecard.tables.ProfilePicURL;
import com.revature.notecard.service.exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getPic(@RequestHeader(value = "Authorization", required = false) String token) {
        Principal prin = service.extractTokenDetails(tokenMaybe);

        long userId = prin.getAuthUserId();
        User user = userRepo.findById(userId).orElseThrow(AuthenticationException::new); // just checks if the user exists
        ProfilePicURL picRaw = picRepo.findById(findById).orElseThrow(RuntimeException::new);
        PicUrlOnly pic = new PicUrlOnly(picRaw);
        return ResponseEntity.status(200).body(mapper.writeValueAsString(pic));


        // if they don't exists, how did the token get there?
//        ProfilePicURL pic = UserRepo.
//        Card newCard = new Card(userId, card.getHtml_q(), card.getHtml_a());
//        System.out.println(newCard);
//        cardRepo.save(newCard);
    }

}
