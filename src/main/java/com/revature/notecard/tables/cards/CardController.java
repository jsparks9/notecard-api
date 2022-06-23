package com.revature.notecard.tables.cards;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.tables.decks.Deck;
import com.revature.notecard.tables.decks.DeckRepository;
import com.revature.notecard.tables.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    private static ObjectMapper mapper = new ObjectMapper();

    private final UserRepository userRepo;

    private final CardRepository cardRepo;

    private final DeckRepository deckRepo;

    @Autowired
    public CardController(UserRepository userRepo, CardRepository cardRepo, DeckRepository deckRepo) {
        this.userRepo = userRepo;
        this.cardRepo = cardRepo;
        this.deckRepo = deckRepo;
    }

    @GetMapping(path="/all")
    public ResponseEntity getCards() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(cardRepo.findAll()));
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal Error", null, HttpStatus.INTERNAL_SERVER_ERROR);        }
    }
}
