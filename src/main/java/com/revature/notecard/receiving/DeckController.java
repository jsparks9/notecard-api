package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.CardQA;
import com.revature.notecard.service.dtos.CreateDeck;
import com.revature.notecard.service.dtos.DeckView;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.service.exceptions.AuthenticationException;
import com.revature.notecard.service.token.TokenService;
import com.revature.notecard.tables.Deck;
import com.revature.notecard.tables.User;
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
    private TokenService service;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public DeckController(DeckRepository deckRepo, UserRepository userRepo, TokenService service) {
        this.deckRepo = deckRepo;
        this.userRepo = userRepo;
        this.service = service;
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

   @ResponseStatus(value = HttpStatus.CREATED)
   @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
   public void newCard(@RequestHeader(value = "Authorization", required = false) String tokenMaybe, @RequestBody CreateDeck deckname) {
       Principal prin = service.extractTokenDetails(tokenMaybe);

       long userId = prin.getAuthUserId();
       User user = userRepo.findById(userId).orElseThrow(AuthenticationException::new); // just checks if the user exists
       // if they don't exists, how did the token get there?
       Deck newDeck = new Deck(user, deckname.getDeckname(), null);
       deckRepo.save(newDeck);
   }
}
