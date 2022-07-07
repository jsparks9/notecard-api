package com.revature.notecard.service;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Encrypt {

    // Helper method to encrypt sensitive data that takes in a string
    // and returns a corresponding hash string
    public static String encrypt(String string) {
        return (string == null) ? null : Hashing.sha256().
                hashString(string, StandardCharsets.UTF_8).toString();
    }
}
