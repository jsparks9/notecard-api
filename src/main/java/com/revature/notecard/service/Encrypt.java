package com.revature.notecard.service;

import org.springframework.lang.Nullable;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    /**
     * Creates an SHA-256 hash of the string input to encrypt sensitive data.
     *
     * @param input string to hash and may be null
     * @return the hexadecimal string representation of the hash, or null if the input was null
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    @Nullable
    public static String encrypt(@Nullable String input) {
        if (input == null) {
            return null;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("The SHA-256 Algorithm is not available.", e);
        }


    }

    /**
     * Converts a byte array hash to a hexadecimal string hash
     *
     * @param hashBytes byte array hash to be converted
     * @return the hexadecimal string representation of the hash
     */
    private static String bytesToHex(byte[] hashBytes) {
        StringBuilder hexString = new StringBuilder();
        for(byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }
        return hexString.toString();
    }
}
