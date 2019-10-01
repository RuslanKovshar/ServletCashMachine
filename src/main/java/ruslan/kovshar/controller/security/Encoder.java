package ruslan.kovshar.controller.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * encodes password
 */
public class Encoder {

    public static String encodePassword(String password) {
        String hashedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return hashedPassword;
    }
}
