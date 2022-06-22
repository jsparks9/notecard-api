package com.revature.notecard.entities;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(columnDefinition = "varchar not null")
    private String html_q;

    @Column(columnDefinition = "varchar")
    private String html_a;



}
