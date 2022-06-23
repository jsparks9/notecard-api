package com.revature.notecard.common.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DeckWithCards {
    private int deck_id;
    private int owner_id;
    private String deckName;
    private List<NoteCard> cards;
}
