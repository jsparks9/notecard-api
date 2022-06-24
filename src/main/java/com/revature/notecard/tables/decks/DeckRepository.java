package com.revature.notecard.tables.decks;

import com.revature.notecard.tables.decks.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {}
