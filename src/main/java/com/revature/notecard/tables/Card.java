package com.revature.notecard.tables;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cards") // Creating a database table entity model for the cards table
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", updatable = false, nullable = false)
    private long card_id;

    @Column(nullable = false)
    private long creator_id;

    @Column(columnDefinition = "varchar not null")
    private String html_q;

    @Column(columnDefinition = "varchar")
    private String html_a;

    public Card(long creator_id, String html_q, String html_a) {
        this.creator_id = creator_id;
        this.html_q = html_q;
        this.html_a = html_a;
    }

    public Card() {
        super();
    }

    public Card(long id, long creator_id, String html_q, String html_a) {
        this(creator_id, html_q, html_a);
        this.card_id = id;
    }


    public long getId() { return card_id; }
    public void setId(long id) { this.card_id = id; }
    public long getCreator() { return creator_id; }
    public void setCreator(long creator_id) { this.creator_id = creator_id; }
    public String getHtml_q() { return html_q; }
    public void setHtml_q(String html_q) { this.html_q = html_q; }
    public String getHtml_a() { return html_a; }
    public void setHtml_a(String html_a) { this.html_a = html_a; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return card_id == card.card_id && Objects.equals(creator_id, card.creator_id) && Objects.equals(html_q, card.html_q) && Objects.equals(html_a, card.html_a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_id, creator_id, html_q, html_a);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + card_id +
                ", creator=" + creator_id +
                ", html_q='" + html_q + '\'' +
                ", html_a='" + html_a + '\'' +
                '}';
    }
}
