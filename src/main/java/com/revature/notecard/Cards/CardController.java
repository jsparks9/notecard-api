package com.revature.notecard.Cards;

import com.revature.notecard.tables.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card") // provides a path for th endpoints within this controller
public class CardController {

    @GetMapping //
    public String sanity(){ return "/test works";}


    public class CardRepository {
        public void saveAll(List<Card> asList) {
        }
    }
}
