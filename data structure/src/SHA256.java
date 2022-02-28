import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA256 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();

        SHA256 sha256 = new SHA256();
        String hash = sha256.getSha256Hash(data);
        System.out.println("Hash is : " + hash);
    }
    /*
    String getSha256Hash(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data.getBytes());
            BigInteger no = new BigInteger(1,hash);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText += "0" + hashText;
            }
            return hashText;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    */
    String getSha256Hash(String data) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }


}
