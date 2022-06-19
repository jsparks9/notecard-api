package com.revature.notecard.utils;

import com.revature.notecard.models.Card;
import com.revature.notecard.models.Deck;
import com.revature.notecard.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.revature.notecard.utils.Encrypt.encrypt;

/**
 * Singleton design for a database placeholder
 * Can retrieve and insert records
 * Data inserted does not persist between restarts.
 */

public class MockData {
    private static List<User> users = new ArrayList<User>();
    private static List<Card> cards = new ArrayList<Card>();
    private static List<Deck> decks = new ArrayList<Deck>();
    private static MockData data;
    private static int userIdGen = 1;
    private static int cardIdGen = 1;
    private static int deckIdGen = 1;

    public static List<User> getUsers() { return users; }
    public static List<Card> getCards() { return cards; }
    public static List<Deck> getDecks() { return decks; }

    public static MockData getInstance() {
        if (data == null) {
            System.out.println("creating instance");
            data = new MockData();
        }
        return data;
    }

    private MockData() {
        System.out.println("Flag");
        User system = new User(0,0,"System","system","system",encrypt("admin"));
        User tester = new User(userIdGen++,1,"Tester@Revature.net","Tester","McTesterson",encrypt("12345"));
        addUser(system);
        addUser(tester);
        Card java1 = new Card(cardIdGen++, "<h1>What is Java</h1>", "<p>Java is a general-purpose, class-based, object-oriented programming language designed for having lesser implementation dependencies.</p>");
        Card java2 = new Card(cardIdGen++,"<h1>In what way does Java employ abstraction?</h1>","<p>Abstraction refers to the quality of dealing with ideas rather than events. It basically deals with hiding the details and showing the essential things to the user. Thus you can say that abstraction in Java is the process of hiding the implementation details from the user and revealing only the functionality to them.</p>");
        Card java3 = new Card(cardIdGen++,"<h1>In what way does Java employ polymorphism?</h1>","<p>Polymorphism is briefly described as “one interface, many implementations”. Polymorphism is a characteristic of being able to assign a different meaning or usage to something in different contexts.</p>");
        Card java4 = new Card(cardIdGen++,"<h1>In what way does Java employ inheritance?</h1>","<p>Inheritance in Java is the concept where the properties of one class can be inherited by the other. It helps to reuse the code and establish a relationship between different classes.</p>");
        Card java5 = new Card(cardIdGen++,"<h1>In what way does Java employ encapsulation?</h1>","<p>Encapsulation is a mechanism where you bind your data(variables) and code(methods) together as a single unit.</p>");
        addCards(java1,java2,java3,java4,java5);
        Deck javaDeck = new Deck(deckIdGen++,system.getId(),"Java Basics", Arrays.asList(java1,java2,java3,java4,java5));
        addDeck(javaDeck);
    }


    public static void addUser(User user) {
        users.add(user);
    }

    public static void addCards(Card ... cardsToAdd) {
        for (Card card : cardsToAdd) {
            cards.add(card);
        }
    }
    public static void addDeck(Deck deck) {
        decks.add(deck);
    }
}
