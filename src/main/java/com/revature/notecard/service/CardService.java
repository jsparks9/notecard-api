package com.revature.notecard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.CardRepository;
import com.revature.notecard.repos.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    ObjectMapper mapper = new ObjectMapper();

    private final CardRepository cardRepo;

    private final DeckRepository deckRepo;


    @Autowired
    public CardService(CardRepository cardRepo, DeckRepository deckRepo) {
        this.cardRepo = cardRepo;
        this.deckRepo = deckRepo;
    }





}
