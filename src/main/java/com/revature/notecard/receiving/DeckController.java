package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.service.dtos.CardQA;
import com.revature.notecard.tables.Card;
import com.revature.notecard.tables.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/deck")
public class DeckController {

    private ObjectMapper mapper = new ObjectMapper();
    private DeckRepository deckRepo;

    @Autowired
    public DeckController(DeckRepository deckRepo) {
        this.deckRepo = deckRepo;
    }
    // 'http://localhost:5000/notecard/deck/id/'+deckId
    @GetMapping(path="/id/{deckId}")
    public ResponseEntity getDeckById(@PathVariable Long deckId) throws JsonProcessingException { // TODO : add to Error Aspect
        Deck deck = deckRepo.getById(deckId);
        if (deck == null) throw new RuntimeException(); // TODO : handle exception in Aspects, make specfic exception
        else {
            List<CardQA> cards = new Stack<CardQA>();
            for (Card card : deck.getCards()) {
                cards.add(new CardQA(card));
            }
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(cards));

        }
    }
}
