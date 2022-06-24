package com.revature.notecard.tables.cards;

import com.revature.notecard.tables.cards.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
//    @Query(value = "from Card where card_id in (select card_id from card_deck where deck_id = :deckID)") // JPQL/HQL
//    List<Card> getCardsByDeck(long deckID);

    @Query(value = "from Card where card_id = :card_id") // JPQL/HQL
    Card getCardByID(long card_id);

    @Query(nativeQuery = true, value = "SELECT * FROM cards WHERE card_id in " +
            "(select card_id from card_deck where deck_id = :deck_id)")
    List<Card> getCardsByDeckId(long deck_id);

}
