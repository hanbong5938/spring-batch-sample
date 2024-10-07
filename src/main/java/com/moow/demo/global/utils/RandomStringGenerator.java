package com.moow.demo.global.utils;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final SecureRandom random;
    private final int length;

    public RandomStringGenerator(int length) {
        this.length = length;
        this.random = new SecureRandom();
    }

    public String generate() {
        StringBuilder sb = new StringBuilder(this.length);
        for (int i = 0; i < this.length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}