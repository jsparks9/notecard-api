package com.revature.notecard.repos;

import com.revature.notecard.tables.Deck;
import com.revature.notecard.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {

    Deck findDeckById(Long id);

    List<Deck> findDeckByCreator(User creator);

    Deck findDeckByDeckname(String deckname);

//    @Query(nativeQuery = true, value = "INSERT INTO decks values(:deck_id, :deck_id,:creator_id")

}
