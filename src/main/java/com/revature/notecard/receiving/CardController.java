package com.revature.notecard.receiving;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.notecard.service.CardService;
import com.revature.notecard.service.dtos.CardDeckRequest;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.CardRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.CardQA;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.service.exceptions.AuthenticationException;
import com.revature.notecard.service.token.JwtConfig;
import com.revature.notecard.service.token.TokenService;
import com.revature.notecard.tables.Card;
import com.revature.notecard.tables.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This class is used to persist a new card to the database.
 * It will check the token of the user to get their ID in order to assign the new card to the current user.
 * Cards aren't automatically assigned to a deck, this process is handled by the DeckController
 */
@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;
    private ObjectMapper mapper = new ObjectMapper();
    private JwtConfig jwtConfig;
    private TokenService service;
    UserRepository userRepo;
    CardRepository cardRepo;

    @Autowired
    public CardController(ObjectMapper mapper, JwtConfig jwtConfig, TokenService service, UserRepository userRepo, CardRepository cardRepo, CardService cardService) {
        this.mapper = mapper;
        this.jwtConfig = jwtConfig;
        this.service = service;
        this.userRepo = userRepo;
        this.cardRepo = cardRepo;
        this.cardService = cardService;
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

    // Creating POST request using CardService method cardToNewDeck to add requested card to deck
    // by card ID and deckname
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/carddeck", consumes = "application/json", produces = "application/json")
    public ResponseEntity cardToNewDeck(@RequestBody CardDeckRequest cardDeckRequest) throws JsonProcessingException {
        return cardService.newCardDeck(cardDeckRequest);
    }
}
