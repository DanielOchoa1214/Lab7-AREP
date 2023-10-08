package org.example.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    public static String toHash(String input) {
        try {
            // get an instance of the SHA-256 message digest algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // compute the hash of the input string
            byte[] hash = md.digest(input.getBytes());
            // convert the hash to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ignored){}
        return "Me dañe D: (Aunque nunca debería llegar aca)";
    }
}
