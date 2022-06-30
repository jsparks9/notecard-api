package com.revature.notecard.tables;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "decks")
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deck_id", updatable = false, nullable = false)
    private long id;

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

    public Deck(long id, User creator, String deckname, List<Card> cards) {
        this(creator, deckname, cards);
        this.id = id;
    }

    public long getId() { return id; }
    public User       getCreator  () { return creator  ; }
    public String     getDeckname () { return deckname ; }
    public List<Card> getCards    () { return cards    ; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(id, deck.id) && Objects.equals(creator, deck.creator) && Objects.equals(deckname, deck.deckname) && Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creator, deckname, cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck_id=" + id +
                ", creator=" + creator +
                ", deckname='" + deckname + '\'' +
                ", cards=" + cards +
                '}';
    }

    public String toStringInfo() {
        return "Deck{" +
                "deck_id='" + id + '\'' +
                ", creator=" + creator.getUsername() +
                ", deckname='" + deckname + '\'' +
                ", amount_of_cards=" + cards.toArray().length +
                '}';
    }
}
