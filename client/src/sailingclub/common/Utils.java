package sailingclub.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
	/**
	 * calculate the MD5 hash of a given string
	 * @param input a string to be hashed
	 * @return the MD5 message digest as string
	 */
	public static String stringToDigest(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static byte[] toByteArray(BufferedImage bi, String format)
	        throws IOException {

	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(bi, format, baos);
	        byte[] bytes = baos.toByteArray();
	        return bytes;

	    }

	    public static BufferedImage toBufferedImage(byte[] bytes)
	        throws IOException {

	        InputStream is = new ByteArrayInputStream(bytes);
	        BufferedImage bi = ImageIO.read(is);
	        return bi;

	    }
}
