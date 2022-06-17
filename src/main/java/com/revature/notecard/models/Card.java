package com.revature.notecard.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Card {
    private int id;
    private String html_q;
    private String html_a;
}
