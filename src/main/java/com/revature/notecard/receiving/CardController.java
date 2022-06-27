package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    private static ObjectMapper mapper = new ObjectMapper();

    private final CardRepository cardRepo;

    @Autowired
    public CardController(CardRepository cardRepo) {
        this.cardRepo = cardRepo;
    }

    @GetMapping(path="/all")
    public ResponseEntity getCards() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(cardRepo.findAll()));
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal Error", null, HttpStatus.INTERNAL_SERVER_ERROR);        }
    }
}
