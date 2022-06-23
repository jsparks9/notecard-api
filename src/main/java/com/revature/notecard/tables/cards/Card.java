package com.revature.notecard.tables.cards;

import com.revature.notecard.tables.users.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "card_id", nullable = false, unique = true)
    private int id;

//    @ManyToOne // many cards can be made by 1 user
//    @JoinColumn(name = "card_creator", nullable = false) // establishes FK
//    private User creator;

    @Column
    private String creator;

    @Column(columnDefinition = "varchar not null")
    private String html_q;

    @Column(columnDefinition = "varchar")
    private String html_a;

    public Card(String creator, String html_q, String html_a) {
        this.creator = creator;
        this.html_q = html_q;
        this.html_a = html_a;
    }

    public Card() { super(); }

    public Card(int id, String creator, String html_q, String html_a) {
        this(creator, html_q, html_a);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCreator() { return creator; }
    public void setCreator(String creator) { this.creator = creator; }
    public String getHtml_q() { return html_q; }
    public void setHtml_q(String html_q) { this.html_q = html_q; }
    public String getHtml_a() { return html_a; }
    public void setHtml_a(String html_a) { this.html_a = html_a; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && Objects.equals(creator, card.creator) && Objects.equals(html_q, card.html_q) && Objects.equals(html_a, card.html_a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creator, html_q, html_a);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", creator=" + creator +
                ", html_q='" + html_q + '\'' +
                ", html_a='" + html_a + '\'' +
                '}';
    }
}
