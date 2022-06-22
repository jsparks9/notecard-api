package com.revature.notecard.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteCard {
    private int id;
    private String html_q;
    private String html_a;
}
