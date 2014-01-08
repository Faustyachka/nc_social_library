/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.registration;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Костя
 */
public class SecurityHash {

    public static String getMd5(String toMd5) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String sbStr = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(toMd5.getBytes("UTF-8"));
            //converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
                sbStr = sb.toString() ;
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityHash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sbStr;
    }
}
