package com.revature.notecard.tables.users;

import com.revature.notecard.tables.cards.Card;
import com.revature.notecard.tables.decks.Deck;
import com.revature.notecard.tables.users.User;
import com.revature.notecard.tables.cards.CardRepository;
import com.revature.notecard.tables.decks.DeckRepository;
import com.revature.notecard.tables.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final String name = this.getClass().getName();
    private final UserRepository userRepo;
    private final CardRepository cardRepo;
    private final DeckRepository deckRepo;

    @Autowired
    public UserController(UserRepository userRepo, CardRepository cardRepo, DeckRepository deckRepo) {
        this.userRepo = userRepo;
        this.cardRepo = cardRepo;
        this.deckRepo = deckRepo;
    }

    @GetMapping(path="/all")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(userRepo.findAll()));
        } catch (JsonProcessingException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            //.setLocation(location);
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<String>("Internal Error", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}
