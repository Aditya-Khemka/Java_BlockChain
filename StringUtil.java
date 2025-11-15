import java.util.Arrays;
import java.security.MessageDigest;

public class StringUtil {
    // Applies SHA-256 to a string and returns the result as a hex string
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder(); 
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // difficulty 5 returns "00000"
    public static String getDifficultyString(int difficulty) {
        char[] chars = new char[difficulty];
        Arrays.fill(chars, '0');
        return new String(chars);
    }
}
