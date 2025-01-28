package com.example.bank.utils;

import java.security.SecureRandom;

public class AccountNumberGenerator {


    public static String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        String prefix = "JUKEGL"; // Use "JUKEGL" as the prefix
        String numbers = String.format("%012x", random.nextLong(1_000_000_000_000L)); // Generate a 12-character hexadecimal number
        return prefix + "_" + numbers; // Combine the prefix and the number
    }

}
