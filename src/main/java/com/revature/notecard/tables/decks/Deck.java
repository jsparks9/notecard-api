package com.revature.notecard.tables.decks;

import com.revature.notecard.tables.cards.Card;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "decks")
public class Deck { //implements Comparable<Deck>{
    @Id
    @Column(unique = true)
    private int deck_id;

//    @OneToMany // many decks can be owned by 1 user
//    @JoinColumn(name="owner_id", nullable=false)
    private String creator;

    @Column
    private String deckname;

    @ManyToMany     // JPA annotations
    @JoinTable(     // maps Java objects to DB objects
            name="card_deck",
            joinColumns = @JoinColumn(name="deck_id"),
            inverseJoinColumns = @JoinColumn(name="card_id")
    )
    private List<Card> cards;

    public Deck() { super(); }

    public Deck(String creator, String deckname, List<Card> cards) {
        this.creator = creator;
        this.deckname = deckname;
        this.cards = cards;
    }

    public Deck(int deck_id, String creator, String deckname, List<Card> cards) {
        this(creator, deckname, cards);
        this.deck_id = deck_id;
    }

    // getters and setters
    public int getDeck_id() { return deck_id; }
    public void setDeck_id(int deck_id) { this.deck_id = deck_id; }
    public String getOwner_id() { return creator; }
    public void setOwner_id(String creator) { this.creator = creator; }
    public String getDeckname() { return deckname; }
    public void setDeckname(String deckname) { this.deckname = deckname; }
    public List<Card> getCards() { return cards; }
    public void setCards(List<Card> cards) { this.cards = cards; }

//    @Override
//    public int compareTo(Deck o) {
//        if (this == o) return 0;
//        if (getDeck_id() != null) {
//            return getDeck_id().compareTo(o.getDeck_id());
//        } else {
//            return -1;
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(deck_id, deck.deck_id) && Objects.equals(creator, deck.creator) && Objects.equals(deckname, deck.deckname) && Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck_id, creator, deckname, cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck_id='" + deck_id + '\'' +
                ", creator=" + creator +
                ", deckname='" + deckname + '\'' +
                ", amount_of_cards=" + cards.toArray().length +
                '}';
    }
}