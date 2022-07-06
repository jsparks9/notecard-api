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


@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // Creating POST request using CardService method cardToNewDeck to add requested card to deck
    // by card ID and deckname
    @PostMapping
    public ResponseEntity cardToNewDeck(@RequestBody CardDeckRequest cardDeckRequest) throws JsonProcessingException {
        return cardService.newCardDeck(cardDeckRequest);
    }

}
