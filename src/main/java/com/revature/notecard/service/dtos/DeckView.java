package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.Deck;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeckView {
    private long id;
    private long owner_id;
    private String deck_name;
    private int numOfCards;

    public DeckView(Deck deck) {
        this.id = deck.getDeck_id();
        this.owner_id = deck.getCreator().getId();
        this.deck_name = deck.getDeckname();
        this.numOfCards = deck.getCards().size();
    }
}
