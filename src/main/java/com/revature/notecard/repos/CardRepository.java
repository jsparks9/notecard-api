package com.revature.notecard.repos;

import com.revature.notecard.tables.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    // Uses custom native query to take in  a card ID and deck ID, and insert the pair into
    // the card_deck table
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into card_deck values(:deckId, :cardId) ;")
    void insertCardIntoDeck(long deckId, long cardId);

}
