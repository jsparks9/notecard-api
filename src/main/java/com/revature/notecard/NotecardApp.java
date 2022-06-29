package com.revature.notecard;

import com.revature.notecard.tables.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.revature.notecard.service.Encrypt.encrypt;

@SpringBootApplication
public class NotecardApp {
    public static void main(String[] args) {
        SpringApplication.run(NotecardApp.class, args);
    }


}
