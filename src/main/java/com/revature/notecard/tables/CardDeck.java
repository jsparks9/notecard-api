package com.revature.notecard.tables;

import javax.persistence.*;

@Entity
@Table(name="card_deck")
public class CardDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "card_id", updatable = false, nullable = false)
    private long card_id;


    @JoinColumn(name = "deck_id", updatable = false, nullable = false)
    private long deck_id;

    public CardDeck() {
    }

    public CardDeck(long card_id, long deck_id) {
        this.card_id = card_id;
        this.deck_id = deck_id;
    }

}
