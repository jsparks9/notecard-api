package com.revature.notecard.receiving;

import com.revature.notecard.repos.CardRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardRepository cardRepo;


    public CardController(CardRepository cardRepo) {
        this.cardRepo = cardRepo;
    }
}
