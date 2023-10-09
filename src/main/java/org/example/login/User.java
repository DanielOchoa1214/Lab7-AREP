package org.example.login;

import java.security.NoSuchAlgorithmException;

public class User {
    private final String username;
    private final String passwordHash;

    /**
     * Constructor of the user class
     * @param username - String with the value to be assigned to this users name
     * @param password - String with the password of the user, this string will never be saved, to save the users info we use the hashed version of the password
     */
    public User(String username, String password) {
        this.username = username;
        this.passwordHash = HashGenerator.toHash(password);
    }

    /**
     * Gets the users stored password hash
     * @return - Hash of the users password
     */
    public String getPasswordHash() {
        return passwordHash;
    }
}
