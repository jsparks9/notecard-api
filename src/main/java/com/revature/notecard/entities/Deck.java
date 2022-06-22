package com.revature.notecard.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "decks")
public class Deck {
    @Id
    @Column(unique = true)
    private int deck_id;
    private int owner_id;
    private String deckname;

}
