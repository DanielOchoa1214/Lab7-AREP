package org.example.facade;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.facade.SecureUrlReader.readURL;

public class SSLContextManager {
    /**
     * Method that sets the context of the SSL protocol in order to trust our personal https servers
     */
    public static void setContext() {
        try {

            // Create a file and a password representation
            File trustStoreFile = new File(getTrustPath());
            char[] trustStorePassword = getKeyStorePassword().toCharArray();

            // Load the trust store, the default type is "pkcs12", the alternative is "jks"
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

            // Get the singleton instance of the TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());

            // Itit the TrustManagerFactory using the truststore object
            tmf.init(trustStore);

            //Print the trustManagers returned by the TMF
            //only for debugging
            for(TrustManager t: tmf.getTrustManagers()){
                System.out.println(t);
            }

            //Set the default global SSLContext so all the connections will use it
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);

            HttpsURLConnection.setDefaultHostnameVerifier ((hostname, session) -> true);

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException |
                 KeyManagementException ex) {
            Logger.getLogger(SecureUrlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
    Method that gets the trust file path of out of the env variables, if not sets the default to "keystores/myTrustStore"
     */
    private static String getTrustPath(){
        if(System.getenv("TRUST-PATH") != null){
            return System.getenv("TRUST-PATH");
        }
        return "keystores/myTrustStore";
    }

    /*
    Method that gets the myTrustStore file password of out of the env variables, if not sets the default to "123456"
     */
    private static String getKeyStorePassword() {
        if(System.getenv("TRUST-PASSWORD") != null){
            return System.getenv("TRUST-PASSWORD");
        }
        return "123456";
    }
}
