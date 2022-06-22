package com.revature.notecard.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "decks")
public class Deck {
    private int deck_id;
    private int owner_id;
    private String deckname;

}
