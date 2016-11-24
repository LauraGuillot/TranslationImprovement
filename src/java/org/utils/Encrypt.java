/**
 * ********************************************************************
 * Class Encrypt
 * Encrypting and decrypting of strings
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.crypto.BadPaddingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {

    /**
     * Key
     */
    private final String key = "asdfghjk98eksW45";

    /**
     * Encrypting of a string
     *
     * @param s a string
     * @return encrypted string
     */
    public String encrypt(String s) {
        try {
            Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, clef);
            return convertString(cipher.doFinal(s.getBytes()));
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        } catch (NoSuchAlgorithmException e) {
            return e.getMessage();
        } catch (NoSuchPaddingException e) {
            return e.getMessage();
        } catch (InvalidKeyException e) {
            return e.getMessage();
        } catch (IllegalBlockSizeException e) {
            return e.getMessage();
        } catch (BadPaddingException e) {
            return e.getMessage();
        }
    }

    /**
     * Decrypting of a string
     *
     * @param s an encrypted string
     * @return decrypted string
     */
    public String decrypt(String s) {
        try {
            Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, clef);
            return new String(cipher.doFinal(convertByte(s)));
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
            return null;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return null;
        } catch (NoSuchPaddingException e) {
            System.out.println(e);
            return null;
        } catch (InvalidKeyException e) {
            System.out.println(e);
            return null;
        } catch (IllegalBlockSizeException e) {
            System.out.println(e);
            return null;
        } catch (BadPaddingException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Conversion of a Byte array into a String
     *
     * @param b Array of bytes
     * @return String
     */
    public String convertString(byte[] b) {
        String tmp = Arrays.toString(b);
        tmp = tmp.substring(1, tmp.length() - 1);
        StringTokenizer st = new StringTokenizer(tmp, ", ");
        String s = "";
        while (st.hasMoreTokens()) {
            s = s + st.nextToken() + "+";
        }
        return s;
    }

    /**
     * Conversion of a string into an array of Bytes
     *
     * @param s String
     * @return Array of bytes
     */
    public byte[] convertByte(String s) {
        byte[] b = {};
        StringTokenizer st = new StringTokenizer(s, "+");

        while (st.hasMoreTokens()) {
            String tmp = st.nextToken();
            if (!tmp.equals("")) {
                b = Arrays.copyOf(b, b.length + 1);
                b[b.length - 1] = Byte.parseByte(tmp);
                
            }
        }
        return b;
    }

}
