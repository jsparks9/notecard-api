package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardView {

    private long id;
    private long creator_id;
    private String html_q;
    private String html_a;

    public CardView(Card card) {
        this.id = card.getId() ;
        this.creator_id = card.getCreator();
        this.html_q = card.getHtml_q();
        this.html_a = card.getHtml_a();
    }
}
