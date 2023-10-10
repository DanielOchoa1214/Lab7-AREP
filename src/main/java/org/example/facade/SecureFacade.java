package org.example.facade;

import java.nio.file.Files;
import java.nio.file.Path;

import static spark.Spark.*;
public class SecureFacade {
    /**
     * Method that sets uo the endpoints in the facade component
     * @param args - Necessary for the main to compile
     */
    public static void main(String[] args) {
        SSLContextManager.setContext();
        port(getPort());
        secure(getKeyStorePath(), getKeyStorePassword(), null, null);
        get("/login", (req, res) -> {
            String user = req.queryMap().get("user").value();
            String password = req.queryMap().get("password").value();
            return login(user, password);
        });
        get("/", (req, res) -> Files.readAllBytes(Path.of("src/main/resources/public/index.html")));
        get("/gif-info.gif", (req, res) -> Files.readAllBytes(Path.of("src/main/resources/public/gif-info.gif")));
    }

    /*
    Method that connects to the SecureLogin component
     */
    private static String login(String username, String passwordHash) throws Exception {
        return SecureUrlReader.readURL(String.format("https://54.210.73.26:35000/login?user=%s&password=%s", username, passwordHash));
    }

    /*
    Method that gets the port of out of the env variables, if not sets the default to 37000
     */
    private static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 37000;
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
