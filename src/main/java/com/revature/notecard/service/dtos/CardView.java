package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardView {

    private long id;
    private long creator_id;
    private String card_q;
    private String card_a;

    public CardView(Card card) {
        this.id = card.getId() ;
        this.creator_id = card.getCreator();
        this.card_q = card.getHtml_q();
        this.card_a = card.getHtml_a();
    }
}
