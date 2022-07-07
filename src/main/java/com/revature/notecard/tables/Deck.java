package com.revature.notecard.tables;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "decks")  // Creating a database table entity model for the decks table
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deck_id", updatable = false, nullable = false)
    private long deck_id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column
    private String deckname;

    @ManyToMany     // JPA annotations
    @JoinTable(     // maps Java objects to DB objects
            name="card_deck",
            joinColumns = @JoinColumn(name="deck_id"),
            inverseJoinColumns = @JoinColumn(name="card_id")
    )
    private List<Card> cards;

    public Deck() {
        super();
    }

    public Deck(User creator, String deckname) {
        this();
        this.creator = creator;
        this.deckname = deckname;
    }
    public Deck(User creator, String deckname, List<Card> cards) {
        this(creator, deckname);
        this.cards = cards;
    }

    public Deck(long deck_id, User creator, String deckname, List<Card> cards) {
        this(creator, deckname, cards);
        this.deck_id = deck_id;
    }

    public long       getDeck_id  () { return deck_id  ; }
    public User       getCreator  () { return creator  ; }
    public String     getDeckname () { return deckname ; }
    public List<Card> getCards    () { return cards    ; }

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
                "deck_id=" + deck_id +
                ", creator=" + creator +
                ", deckname='" + deckname + '\'' +
                ", cards=" + cards +
                '}';
    }

    public String toStringInfo() {
        return "Deck{" +
                "deck_id='" + deck_id + '\'' +
                ", creator=" + creator.getUsername() +
                ", deckname='" + deckname + '\'' +
                ", amount_of_cards=" + cards.toArray().length +
                '}';
    }
}
