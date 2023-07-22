package TheCarRentalProject.utils;

import org.springframework.context.annotation.Configuration;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

@Configuration
public class KeyGeneratorExample {
    public static void main(String[] args) {
        try {
            // Create a KeyGenerator instance for HMAC-SHA-512 algorithm
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");

            // Generate a secure random key with a key size of 512 bits (you can adjust the size as needed)
            keyGenerator.init(512);
            SecretKey secretKey = keyGenerator.generateKey();

            // Get the encoded key in bytes (you can store this securely, e.g., in an environment variable)
            byte[] encodedKey = secretKey.getEncoded();

            // Print the encoded key (for demonstration purposes)
            String base64EncodedKey = java.util.Base64.getUrlEncoder().encodeToString(encodedKey);
            System.out.println("Encoded Key: " + base64EncodedKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

