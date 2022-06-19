package com.revature.notecard.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Deck {
    private int deck_id;
    private int owner_id;
    private String deckname;
    // next var for convenience
    private List<Card> cards;
}
