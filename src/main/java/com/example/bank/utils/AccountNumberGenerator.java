package com.example.bank.utils;

import java.security.SecureRandom;

public class AccountNumberGenerator {


    public static String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder letters = new StringBuilder("ej"); // Start with "ej"
        for (int i = 0; i < 4; i++) { // Generate 4 additional random letters
            letters.append((char) ('a' + random.nextInt(26)));
        }
        String numbers = String.format("%012d", random.nextLong(1_000_000_000_000L));
        return letters + "_" + numbers;
    }


}
