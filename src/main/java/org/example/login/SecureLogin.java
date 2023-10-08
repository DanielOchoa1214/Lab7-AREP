package org.example.login;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class SecureLogin {
    private static Map<String, User> users = new HashMap<>(Map.of(
            "rene", new User("rene", "atrevetete"),
            "ken", new User("ken", "mojo dojo casa house")
    ));

    public static void main(String[] args) {
        port(getPort());
        secure(getKeyStorePath(), getKeyStorePassword(), null, null);
        get("/login", (req, res) -> {
            String user = req.queryParams("user");
            String password = req.queryParams("password");
            return login(user, password);
        });
    }

    private static boolean login(String username, String password){
        return users.containsKey(username) && users.get(username).getPasswordHash().equals(HashGenerator.toHash(password));
    }

    private static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

    private static String getKeyStorePath() {
        if(System.getenv("KEYSTORE-PATH") != null){
            return System.getenv("KEYSTORE-PATH");
        }
        return "keystores/ecikeystore.p12";
    }

    private static String getKeyStorePassword() {
        if(System.getenv("KEYSTORE-PASSWORD") != null){
            return System.getenv("KEYSTORE-PASSWORD");
        }
        return "123456";
    }
}
