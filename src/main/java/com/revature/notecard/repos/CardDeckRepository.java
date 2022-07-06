package com.revature.notecard.repos;

import com.revature.notecard.tables.CardDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDeckRepository extends JpaRepository<CardDeck, Long> {
}
