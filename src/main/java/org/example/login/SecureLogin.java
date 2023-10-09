package org.example.login;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class SecureLogin {
    private static Map<String, User> users = new HashMap<>(Map.of(
            "rene", new User("rene", "atrevetete"),
            "ken", new User("ken", "mojo dojo casa house")
    ));

    /**
     * Method that sets up the endpoints in the Login component
     * @param args - Necessary for the main to compile
     */
    public static void main(String[] args) {
        port(getPort());
        secure(getKeyStorePath(), getKeyStorePassword(), null, null);
        get("/login", (req, res) -> {
            String user = req.queryParams("user");
            String password = req.queryParams("password");
            return login(user, password);
        });
    }

    /*
    Method that checks if the given user and password are correct
     */
    private static boolean login(String username, String password){
        return users.containsKey(username) && users.get(username).getPasswordHash().equals(HashGenerator.toHash(password));
    }

    /*
    Method that gets the port of out of the env variables, if not sets the default to 35000
     */
    private static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

    /*
    Method that gets the keyStore file path of out of the env variables, if not sets the default to "keystores/ecikeystore.p12"
     */
    private static String getKeyStorePath() {
        if(System.getenv("KEYSTORE-PATH") != null){
            return System.getenv("KEYSTORE-PATH");
        }
        return "keystores/ecikeystore.p12";
    }

    /*
    Method that gets the keyStore file password of out of the env variables, if not sets the default to "123456"
     */
    private static String getKeyStorePassword() {
        if(System.getenv("KEYSTORE-PASSWORD") != null){
            return System.getenv("KEYSTORE-PASSWORD");
        }
        return "123456";
    }
}
