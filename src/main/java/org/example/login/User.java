package org.example.login;

import java.security.NoSuchAlgorithmException;

public class User {
    private final String username;
    private final String passwordHash;

    public User(String username, String password) {
        this.username = username;
        this.passwordHash = HashGenerator.toHash(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
