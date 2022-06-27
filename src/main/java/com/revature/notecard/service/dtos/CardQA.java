package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardQA {
    private String html_q;
    private String html_a;

    public CardQA(Card card) {
        this.html_q = card.getHtml_q();
        this.html_a = card.getHtml_a();
    }
}
