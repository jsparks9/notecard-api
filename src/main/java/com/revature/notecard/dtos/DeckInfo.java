package com.revature.notecard.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeckInfo {
    private int deck_id;
    private int owner_id;
    private String deckName;
    private int cardsInDeck;
}
