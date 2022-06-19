package com.revature.notecard;


import com.revature.notecard.utils.MockData;

import java.sql.Connection;

public class TestMain {
    public static void main(String[] args) {
//        JDBC jdbc = JDBC.getInstance();
//        jdbc = JDBC.getInstance();
        MockData data = MockData.getInstance();
        data = MockData.getInstance();
        data = MockData.getInstance();
        System.out.println(data.getUsers());
        System.out.println(data.getCards());
        System.out.println(data.getDecks());
    }
}
