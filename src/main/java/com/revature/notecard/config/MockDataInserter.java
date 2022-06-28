package com.revature.notecard.config;

import com.revature.notecard.tables.Card;
import com.revature.notecard.tables.Deck;
import com.revature.notecard.tables.User;
import com.revature.notecard.repos.CardRepository;
import com.revature.notecard.repos.DeckRepository;
import com.revature.notecard.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.revature.notecard.service.Encrypt.encrypt;

@Component
public class MockDataInserter implements CommandLineRunner {
    private final UserRepository userRepo;
    private final CardRepository cardRepo;
    private final DeckRepository deckRepo;

    @Autowired
    public MockDataInserter(UserRepository userRepo, CardRepository cardRepo, DeckRepository deckRepo) {
        this.userRepo = userRepo;
        this.cardRepo = cardRepo;
        this.deckRepo = deckRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        User user0 = new User("System@revature.net","system","system",encrypt("system"), User.Role.ADMIN);
//        User user1 = new User("Admin@revature.net", "Tester", "Mctest", encrypt("admin"), User.Role.ADMIN);
//        User user2 = new User("Tester@revature.net", "Tester", "Mctest", encrypt("admin"), User.Role.BASIC);
//        User user3 = new User("Troll@revature.net", "Troll", "McTroll", encrypt("trololol"), User.Role.BANNED);
        User user0 = new User("System@revature.net","system","system",encrypt("system"));
        User user1 = new User("Admin@revature.net", "Tester", "Mctest", encrypt("admin"));
        User user2 = new User("Tester@revature.net", "Tester", "Mctest", encrypt("pass123"));
        User user3 = new User("Troll@revature.net", "Troll", "McTroll", encrypt("trololol"));
        user0.setRole(User.Role.ADMIN);
        user1.setRole(User.Role.ADMIN);
        user2.setRole(User.Role.BASIC);
        user3.setRole(User.Role.BANNED);


        userRepo.saveAll(Arrays.asList(user0, user1, user2, user3));
//        userRepo.saveAll(Arrays.asList(user0));
//        userRepo.saveAll(Arrays.asList(user1));
//        userRepo.saveAll(Arrays.asList(user2));
//        userRepo.saveAll(Arrays.asList(user3));

        Card test1 = new Card(user0.getId(),"Test Question 1", "Test Answer 1");
        Card test2 = new Card(user0.getId(),"Test Question 2", "Test Answer 2");
        List<Card> deck0cards = Arrays.asList(test1, test2); // , test2
//        cardRepo.saveAll(deck0cards);
        cardRepo.saveAll(Arrays.asList(test1));
        cardRepo.saveAll(Arrays.asList(test2));

        Deck deck0 = new Deck(user0,"Test Deck", deck0cards);
//        Deck deck0 = new Deck(user0,"Test Deck", Arrays.asList());


        /*     Java cards           */
        Card java1 = new Card(user1.getId(), "<h1>What is Java</h1>", "<p>Java is a general-purpose, class-based, object-oriented programming language designed for having lesser implementation dependencies.</p>");
        Card java2 = new Card(user1.getId(),"<h1>In what way does Java employ abstraction?</h1>","<p>Abstraction refers to the quality of dealing with ideas rather than events. It basically deals with hiding the details and showing the essential things to the user. Thus you can say that abstraction in Java is the process of hiding the implementation details from the user and revealing only the functionality to them.</p>");
        Card java3 = new Card(user1.getId(),"<h1>In what way does Java employ polymorphism?</h1>","<p>Polymorphism is briefly described as “one interface, many implementations”. Polymorphism is a characteristic of being able to assign a different meaning or usage to something in different contexts.</p>");
        Card java4 = new Card(user1.getId(),"<h1>In what way does Java employ inheritance?</h1>","<p>Inheritance in Java is the concept where the properties of one class can be inherited by the other. It helps to reuse the code and establish a relationship between different classes.</p>");
        Card java5 = new Card(user1.getId(),"<h1>In what way does Java employ encapsulation?</h1>","<p>Encapsulation is a mechanism where you bind your data(variables) and code(methods) together as a single unit.</p>");

        List<Card> deck1cards = Arrays.asList(java1,java2,java3,java4,java5);
        cardRepo.saveAll(deck1cards);
        Deck deck1 = new Deck(user1,"Java OOP", deck1cards);


        /*     SQL cards           */
        Card sql1 = new Card(user1.getId(),"<h1>What is SQL</h1>","<h1>Structured Query Language</h1>");
        Card sql2 = new Card(user1.getId(),"<h1>What is DDL</h1>","<h1>Data Definition Language</h1>\n" +
                "\t<ul>\n" +
                "\t<li>create</li>\n" +
                "\t<li>alter</li>\n" +
                "\t<li>truncate</li>\n" +
                "\t<li>drop</li>\n" +
                "\t</ul>");
        Card sql3 = new Card(user1.getId(),"<h1>What is TCL</h1>","<h1>Transaction Control Language</h1>\n" +
                "\t<ul>\n" +
                "\t<li>savepoint</li>\n" +
                "\t<li>commit</li>\n" +
                "\t<li>rollback</li>\n" +
                "\t</ul>");
        Card sql4 = new Card(user1.getId(),"<h1>What is DML</h1>","<h1>Data Manipulation Language</h1>\n" +
                "\t<ul>\n" +
                "\t<li>select</li>\n" +
                "\t<li>insert</li>\n" +
                "\t<li>update</li>\n" +
                "\t<li>delete</li>\n" +
                "\t</ul>");
        Card sql5 = new Card(user1.getId(),"<h1>What is DCL</h1>","<h1>Data Control Language</h1>\n" +
                "\t<ul>\n" +
                "\t<li>grant</li>\n" +
                "\t<li>revoke</li>\n" +
                "\t</ul>");

        List<Card> deck2cards = Arrays.asList(sql1,sql2,sql3,sql4,sql5);
        cardRepo.saveAll(deck2cards);
        Deck deck2 = new Deck(user1,"SQL Lang", deck2cards);



        /*     ReactJS cards           */
        Card react1 = new Card(user2.getId(),"<h1>What is React? Is it a library or framework? What's the difference between those?</h1>","<h1>React is a UI library. It's a library not a framework because you call it in your code; it can be integrated into part of or the entire UI</h1>");
        Card react2 = new Card(user2.getId(),"<h1>Why use React?</h1>","" +
                "\t<ul>\n" +
                "\t<li>We use it to make single page front end applications</li>\n" +
                "\t<li>Lets us dynamically create and render components without having to refresh pages</li>\n" +
                "\t</ul>");
        Card react3 = new Card(user2.getId(),"<h1>What is the difference between React and ReactDOM?</h1>","" +
                "\t<ul>\n" +
                "\t<li>React is a higher level package for both ReactDOM and React Native</li>\n" +
                "\t<li>ReactDOM is strictly the web implementation of React</li>\n" +
                "\t</ul>");
        Card react4 = new Card(user2.getId(),"<h1>How many HTML pages does our React App use?</h1>","" +
                "\t<ul>\n" +
                "\t<li>We render in one SINGLE page</li>\n" +
                "\t<li>It is constructed in a way that we only ever need to render one DOM object</li>\n" +
                "\t</ul>");
        Card react5 = new Card(user2.getId(),"<h1>What is SPA?</h1>","<h1>Single Page Application is a website design approach where each new page's content is served not from loading new HTML pages but generated dynamically with JS's ability to manipulate the DOM elements on the existing page itself</h1>");
        Card react6 = new Card(user2.getId(),"<h1>What are some benefits of SPA?</h1>","<h1>Allows users to contineu consuming and interacting with the page while new elements are being updated or fetched, and can result in much faster interactions</h1>");
        Card react7 = new Card(user2.getId(),"<h1>What are some downsides of SPA?</h1>","" +
                "\t<ul>\n" +
                "\t<li>Accessibility</li>\n" +
                "\t<li>SEO rankings</li>\n" +
                "\t<li>If your content is purely static, it can worsen initial load times</li>\n" +
                "\t</ul>");
        Card react8 = new Card(user2.getId(),"<h1>What is the package.json?</h1>","" +
                "\t<ul>\n" +
                "\t<li>Lists our dependencies</li>\n" +
                "\t<li>Lists our scripts (Start, test are aliases for npm run [script])</li>\n" +
                "\t<li>Run the build script to show the target folder</li>\n" +
                "\t</ul>");
        Card react9 = new Card(user2.getId(),"<h1>What are node_modules?</h1>","<h1>Houses our dependency files</h1>");

        List<Card> deck3cards = Arrays.asList(react1,react2,react3,react4,react5,react6,react7,react8,react9);
        cardRepo.saveAll(deck3cards);
        Deck deck3 = new Deck(user2,"ReactJS", deck3cards);



        /*     HTML cards           */
        Card html1 = new Card(user2.getId(),"<h1>What is HTML</h1>","" +
                "\t<ul>\n" +
                "\t<li>Hypertext Markup Language</li>\n" +
                "\t<li>HTML does not perform any logic for you</li>\n" +
                "\t<li>You can not create web pages without using HTML</li>\n" +
                "\t</ul>");
        Card html2 = new Card(user2.getId(),"<h1>What makes an HTML page an HTML page?</h1>","" +
                "\t<ul>\n" +
                "\t<li>Elements</li>\n" +
                "\t<li>Attributes</li>\n" +
                "\t</ul>");
        Card html3 = new Card(user2.getId(),"<h1>Describe Elements</h1>","" +
                "\t<ul>\n" +
                "\t<li>Elements are created via \"tags\" in your html page</li>\n" +
                "\t<li>Some tags include an opening and closing tag, others just a single tag</li>\n" +
                "\t</ul>");
        Card html4 = new Card(user2.getId(),"<h1>Describe Attributes</h1>","" +
                "\t<ul>\n" +
                "\t<li>Affect your elements</li>\n" +
                "\t<li>Attributes work very well with your styling</li>\n" +
                "\t<li>They follow a key:value pairing system</li>\n" +
                "\t</ul>");
        Card html5 = new Card(user2.getId(),"<h1>List some common elements</h1>","" +
                "\t<ul>\n" +
                "\t<li>div: defines a “division” of your page</li>\n" +
                "\t<li>p: defines a paragraph</li>\n" +
                "\t<li>span: inline tag for grouping texts or elements</li>\n" +
                "\t<li>b: bold text</li>\n" +
                "\t<li>i: italicized text</li>\n" +
                "\t<li>h1, h2, ... h6: headings</li>\n" +
                "\t<li>br: line break</li>\n" +
                "\t<li>table: defines a table</li>\n" +
                "\t<li>th: table header(think column name)</li>\n" +
                "\t<li>tr: table row (holds a row of data)</li>\n" +
                "\t<li>td: table data (individual elements of a row)</li>\n" +
                "\t<li>img src=\"URL\": adds an image to your page</li>\n" +
                "\t<li>ol: an ordered list (number bullets)</li>\n" +
                "\t<li>li: list item</li>\n" +
                "\t<li>ul: an unordered list (bullets)</li>\n" +
                "\t<li>li: list item</li>\n" +
                "\t<li>a href=\"URL\": makes a hyperlink</li>\n" +
                "\t</ul>");
        Card html6 = new Card(user2.getId(),"<h1>List some common attributes</h1>","" +
                "\t<ul>\n" +
                "\t<li>id: a unique identifier for an element</li>\n" +
                "\t<li>class: a title given for a unique set of styling options</li>\n" +
                "\t<li>src: used with the img tag, you provide the link to your image with this attribute</li>\n" +
                "\t<li>href: used with the anchor tag, you provide a link to a new file or web page with this attribute</li>\n" +
                "\t</ul>");

        List<Card> deck4cards = Arrays.asList(html1,html2,html3,html4,html5,html6);
        cardRepo.saveAll(deck4cards);
        Deck deck4 = new Deck(user2,"HTML", deck4cards);

        deckRepo.saveAll(Arrays.asList(deck0, deck1, deck2, deck3, deck4));

//        mkln(50);
//        System.out.println("Printing all users : ");
//        System.out.println(userRepo.findAll());
//        mkln(50);
//        System.out.println("Printing all cards : ");
//        System.out.println(cardRepo.findAll());
//        mkln(50);
//        System.out.println("Printing all decks : ");
//        System.out.println(deckRepo.findAll());
//        mkln(50);
    }

    private void mkln(int i) {
        System.out.println(new String(new char[i]).replace("\0", "*"));
    }

}
