package com.revature.notecard.repos;

import com.revature.notecard.tables.Card;
import com.revature.notecard.tables.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {

    List<Deck> findDeckById(Long id);

//    @Query(nativeQuery = true, value = "INSERT INTO desks values(:deck_id, :deck_id,:creator_id")



}
