package com.revature.notecard.receiving;

import com.revature.notecard.repos.CardRepository;
import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.CardQA;
import com.revature.notecard.service.dtos.Principal;
import com.revature.notecard.service.exceptions.AuthenticationException;
import com.revature.notecard.tables.Card;
import com.revature.notecard.tables.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    UserRepository userRepo;
    CardRepository cardRepo;


    public CardController(UserRepository userRepo, CardRepository cardRepo) {
        this.userRepo = userRepo;
        this.cardRepo = cardRepo;
    }


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "create", consumes = "application/json", produces = "application/json")
    public void newCard(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody CardQA card) {
        System.out.println("The token is: " + token);
        System.out.println("The Card object is: " + card);

        long userId=1; //Principal  we need to get the user's id number to assign it to the card
        User user = userRepo.findById(userId).orElseThrow(AuthenticationException::new);
        Card newCard = new Card(user, card.getHtml_q(), card.getHtml_a());

        cardRepo.save(newCard);
    }




}
