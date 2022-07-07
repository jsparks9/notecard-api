package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.CardQA;
import com.revature.notecard.service.dtos.DeckView;
import com.revature.notecard.service.dtos.NewDeck;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.service.exceptions.AuthenticationException;
import com.revature.notecard.service.token.JwtConfig;
import com.revature.notecard.service.token.TokenService;
import com.revature.notecard.tables.Deck;
import com.revature.notecard.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deck")
public class DeckController {


    private DeckRepository deckRepo;
    private UserRepository userRepo;
    private JwtConfig jwtConfig;
    private TokenService service;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public DeckController(DeckRepository deckRepo, JwtConfig jwtConfig,TokenService service, UserRepository userRepo) {
        this.deckRepo = deckRepo;
        this.userRepo = userRepo;
        this.jwtConfig = jwtConfig;
        this.service = service;
    }

    @ResponseStatus(value = HttpStatus.CREATED) // Anyone can view; no token needed
    @PostMapping(path="/view", consumes = "application/json", produces = "application/json" )
    public void viewAllDecks(@RequestHeader(value = "Authorization", required = false) String tokenMaybe, @RequestBody NewDeck deckName) {


        Principal princ = service.extractTokenDetails(tokenMaybe);

        long userId = princ.getAuthUserId();
        User user = userRepo.findById(userId).orElseThrow(AuthenticationException::new);
        Deck deck = new Deck(user, deckName.getDeckname());
        deckRepo.save(deck);
        //List<DeckView> decks = deckRepo.findAll().stream().map(DeckView::new).collect(Collectors.toList());
        //return ResponseEntity.status(200).body(mapper.writeValueAsString(decks));
    }
}




