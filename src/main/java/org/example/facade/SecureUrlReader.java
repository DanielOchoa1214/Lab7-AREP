package org.example.facade;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecureUrlReader {
    /**
     * Method that makes a remote invocation of a method via the URL string
     * @param url - url of the endpoint
     * @return Returns the result of the consult in String form
     * @throws Exception - If the URL is malformed or something i can't predict happens
     */
    public static String readURL(String url) throws Exception {
        String site = url;
        // Crea el objeto que representa una URL
        URL siteURL = new URL(site);
        // Crea el objeto que URLConnection
        URLConnection urlConnection = siteURL.openConnection();
        // Obtiene los campos del encabezado y los almacena en un estructura Map
        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        // Obtiene una vista del mapa como conjunto de pares <K,V>
        // para poder navegarlo
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
        // Recorre la lista de campos e imprime los valores
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();
            //Si el nombre es nulo, significa que es la linea de estado
            if (headerName != null) {
                System.out.print(headerName + ":");
            }
            List<String> headerValues = entry.getValue();
            for (String value : headerValues) {
                System.out.print(value + "\n");
            }
        }

        System.out.println("-------message-body------");
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String inputLine = null;
        StringBuilder response = new StringBuilder();
        try (reader) {
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                response.append(inputLine);
            }
        } catch (IOException x) {
            System.err.println(x);
        }

        return response.toString();
    }
}
