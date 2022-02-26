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

/**
 * this class contains some methods usefull for 
 *activities on both client and server
 */
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
	
	/**
	 * convert an image to a bytes array
	 * @param bi the image
	 * @param format the image format (jpg,png,webp ...)
	 * @return the array of byte of the image
	 * @throws IOException
	 */
	public static byte[] toByteArray(BufferedImage bi, String format)
	        throws IOException {

	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(bi, format, baos);
	        byte[] bytes = baos.toByteArray();
	        return bytes;

	}

	/**
	 * converts byte array to an image
	 * @param bytes the array of byte of the image
	 * @return an image converted from an array of bytes
	 * @throws IOException
	 */
    public static BufferedImage toBufferedImage(byte[] bytes)
        throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;
    }
}
