package com.revature.notecard;

import com.revature.notecard.entities.Card;
import com.revature.notecard.entities.User;
import com.revature.notecard.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;

import static com.revature.notecard.utils.Encrypt.encrypt;

@SpringBootApplication
public class NotecardApp implements CommandLineRunner {
    private final UserRepository userRepo;

    @Autowired
    public NotecardApp(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(NotecardApp.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user0 = new User(UUID.randomUUID().toString(), 0,
                "System@revature.net", "", "", encrypt("system"));
        User user1 = new User(UUID.randomUUID().toString(), 2,
                        "Admin@revature.net", "Tester", "Mctest", encrypt("admin"));
        User user2 = new User(UUID.randomUUID().toString(), 1,
                "McTester@revature.net", "Tester", "Mctest", encrypt("12345"));
        User user3 = new User(UUID.randomUUID().toString(), 1,
                "AnotherUser@revature.net", "Another", "User", encrypt("12345"));
        userRepo.saveAll(Arrays.asList(user1, user2, user3));

        Card card1 = new Card(user0, "What is Java?", "Java is what this is running in");
        Card card2 = new Card(user0, "What is SQL?", "Structured Query Language");

        System.out.println(userRepo.findAll());
    }

}
