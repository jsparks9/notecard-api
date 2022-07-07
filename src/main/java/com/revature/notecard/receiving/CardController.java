package com.revature.notecard.receiving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.CardRepository;
import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.CardQA;
import com.revature.notecard.service.dtos.CardView;
import com.revature.notecard.service.dtos.DeckRequest;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.service.exceptions.AuthenticationException;
import com.revature.notecard.service.token.JwtConfig;
import com.revature.notecard.service.token.TokenService;
import com.revature.notecard.tables.Card;
import com.revature.notecard.tables.Deck;
import com.revature.notecard.tables.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to persist a new card to the database.
 * It will check the token of the user to get their ID in order to assign the new card to the current user.
 * Cards aren't automatically assigned to a deck, this process is handled by the DeckController
 */
@RestController
@RequestMapping("/card")
public class CardController {

    private ObjectMapper mapper = new ObjectMapper();
    private JwtConfig jwtConfig;
    private TokenService service;
    UserRepository userRepo;
    CardRepository cardRepo;
    DeckRepository deckRepo;


    public CardController(ObjectMapper mapper, JwtConfig jwtConfig, TokenService service, UserRepository userRepo, CardRepository cardRepo) {
        this.mapper = mapper;
        this.jwtConfig = jwtConfig;
        this.service = service;
        this.userRepo = userRepo;
        this.cardRepo = cardRepo;
    }


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void newCard(@RequestHeader(value = "Authorization", required = false) String tokenMaybe, @RequestBody CardQA card) {
        System.out.println("The token is: " + tokenMaybe);
        System.out.println("The Card object is: " + card);
        Principal prin = service.extractTokenDetails(tokenMaybe);

        long userId = prin.getAuthUserId();
        User user = userRepo.findById(userId).orElseThrow(AuthenticationException::new); // just checks if the user exists
        // if they don't exists, how did the token get there?
        Card newCard = new Card(userId, card.getHtml_q(), card.getHtml_a());
        System.out.println(newCard);
        cardRepo.save(newCard);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/view", produces="application/json")
    public ResponseEntity viewAllCards() throws JsonProcessingException {
        List<CardView> cards = cardRepo.findAll().stream().map(CardView::new).collect(Collectors.toList());
        return ResponseEntity.status(200).body(mapper.writeValueAsString(cards));
    }
}
