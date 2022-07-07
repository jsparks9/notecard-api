package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.CardQA;
import com.revature.notecard.service.dtos.DeckView;
import com.revature.notecard.tables.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // Making the class a rest controller to leverage spring
@RequestMapping("/deck") // Mapping the endpoint '/deck'
public class DeckController {
    private DeckRepository deckRepo;
    private UserRepository userRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public DeckController(DeckRepository deckRepo, UserRepository userRepo) {
        this.deckRepo = deckRepo;
        this.userRepo = userRepo;
    }

    // GET request at endpoint '/view' the returns a list of all the current decks.
    @ResponseStatus(HttpStatus.OK) // Anyone can view; no token needed
    @GetMapping(path="/view", produces = "application/json")
    public ResponseEntity viewAllDecks() throws JsonProcessingException {
        List<DeckView> decks = deckRepo.findAll().stream().map(DeckView::new).collect(Collectors.toList());
        return ResponseEntity.status(200).body(mapper.writeValueAsString(decks));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path="/id/{deckId}", produces = "application/json")
    public ResponseEntity getDeckById(@PathVariable String deckId) throws JsonProcessingException {
        Deck deck = deckRepo.findById(Long.parseLong(deckId)).orElseThrow(RuntimeException::new);
        List<CardQA> cards = deck.getCards().stream().map(CardQA::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(cards)); // OK = 200
    }

}
