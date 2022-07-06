package com.revature.notecard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.CardDeckRepository;
import com.revature.notecard.repos.CardRepository;
import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.service.dtos.CardDeckRequest;
import com.revature.notecard.tables.CardDeck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    ObjectMapper mapper = new ObjectMapper();

    private final CardRepository cardRepo;

    private final DeckRepository deckRepo;

    private final CardDeckRepository cardDeckRepo;

    @Autowired
    public CardService(CardRepository cardRepo, DeckRepository deckRepo, CardDeckRepository cardDeckRepo) {
        this.cardRepo = cardRepo;
        this.deckRepo = deckRepo;
        this.cardDeckRepo = cardDeckRepo;
    }

    // Takes in card ID and deck ID, find card by ID, find deck by name,
    // add card to corresponding decks list of cards
    public ResponseEntity newCardDeck(CardDeckRequest cardDeckRequest) throws JsonProcessingException {

        CardDeck newCardDeck = new CardDeck(cardDeckRequest.getCard_id(), cardDeckRequest.getDeck_id());
        cardDeckRepo.save(newCardDeck);
        return ResponseEntity.status(201).body(mapper.writeValueAsString(newCardDeck.toString()));

    }



}
