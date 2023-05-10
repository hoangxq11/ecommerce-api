package com.demo.service.utils;

import com.demo.web.dto.request.SignupRequest;

import java.util.*;

public class SignupRequestGenerator {
    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int MAX_EMAIL_LENGTH = 50;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 40;

    private static final char[] ALPHANUMERIC_CHARACTERS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private final int n;
    private final Random random;

    public SignupRequestGenerator(int n) {
        this.n = n;
        this.random = new Random();
    }

    public List<SignupRequest> generate() {
        Set<String> usernames = new HashSet<>();
        Set<String> emails = new HashSet<>();

        List<SignupRequest> requests = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String username = generateUniqueUsername(usernames);
            String email = username + "@gmail.com";
            String password = username;

            SignupRequest request = new SignupRequest();
            request.setUsername(username);
            request.setEmail(email);
            request.setAuthorities(Collections.singleton("ROLE_CUSTOMER"));
            request.setPassword(password);

            requests.add(request);
        }
        return requests;
    }

    private String generateUniqueUsername(Set<String> existingUsernames) {
        String username;
        do {
            username = generateRandomString(MIN_USERNAME_LENGTH, MAX_USERNAME_LENGTH);
        } while (existingUsernames.contains(username));
        existingUsernames.add(username);
        return username;
    }

    private String generateUniqueEmail(Set<String> existingEmails) {
        String email;
        do {
            email = generateRandomString(MAX_EMAIL_LENGTH);
        } while (existingEmails.contains(email));
        existingEmails.add(email);
        return email;
    }

    private String generateRandomString(int length) {
        return generateRandomString(length, length);
    }

    private String generateRandomString(int minLength, int maxLength) {
        int length = minLength + random.nextInt(maxLength - minLength + 1);
        char[] buffer = new char[length];
        for (int i = 0; i < length; i++) {
            buffer[i] = ALPHANUMERIC_CHARACTERS[random.nextInt(ALPHANUMERIC_CHARACTERS.length)];
        }
        return new String(buffer);
    }
}
