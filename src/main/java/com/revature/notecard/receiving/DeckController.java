package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.service.dtos.CardQA;
import com.revature.notecard.service.exceptions.ResourceNotFoundException;
import com.revature.notecard.tables.Card;
import com.revature.notecard.tables.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/deck")
public class DeckController {

    private static ObjectMapper mapper = new ObjectMapper();
    private final DeckRepository deckRepo;

    @Autowired
    public DeckController(DeckRepository deckRepo) {
        this.deckRepo = deckRepo;
    }

    @GetMapping(path="/id/{deckId}")
    public ResponseEntity getQAbyDeckId(@PathVariable Long deckId) throws JsonProcessingException {
        Deck deck = deckRepo.findById(deckId).orElseThrow(ResourceNotFoundException::new);
        List<CardQA> cards = new Stack<CardQA>();
        for (Card card : deck.getCards()) {
            cards.add(new CardQA(card));
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(cards));
        //.stream().map(CardQA::new)
    }

}
