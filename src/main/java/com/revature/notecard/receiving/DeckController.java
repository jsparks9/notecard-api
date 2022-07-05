package com.revature.notecard.receiving;

import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.NewDeck;
import com.revature.notecard.service.exceptions.AuthenticationException;
import com.revature.notecard.tables.Deck;
import com.revature.notecard.tables.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deck")
public class DeckController {

    UserRepository userRepo;
    DeckRepository deckRepo;

    public DeckController(UserRepository userRepo, DeckRepository deckRepo) {
        this.userRepo = userRepo;
        this.deckRepo = deckRepo;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json" )
    //public ResponseEntity nuvodeck(@RequestBody Object o) {
    public void nuvodeck(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody NewDeck deal) {
        System.out.println("The token is: " + token);
        System.out.println("The NewDeck Object is: " + deal);

        // get Principle from token with : extractTokenDetails(token);
        // got userId from Principle.getAuthUserId
        long userId = 1;
        User user = userRepo.findById(userId).orElseThrow(AuthenticationException::new);

        Deck deck = new Deck(user,deal.getDeckname(), null);

        deckRepo.save(deck);



    }
