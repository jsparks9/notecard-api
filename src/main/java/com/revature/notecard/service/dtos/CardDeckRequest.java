package com.revature.notecard.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDeckRequest {

    private long card_id;
    private long deck_id;
}
