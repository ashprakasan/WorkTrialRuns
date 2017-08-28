package com.company;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class AuthUtilities {
    static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    static String calculateHashcode(String key, String token) {
        String hashcode = null;
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            hashcode = Hex.encodeHexString(sha256_HMAC.doFinal(token.getBytes("UTF-8")));
        } catch (InvalidKeyException e) {
            System.out.println("Key passed to HMAC is invalid.");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e){
            System.out.println("Invalid Hashcode generation algorithm.");
            e.printStackTrace();
        }catch(UnsupportedEncodingException e){
            System.out.println("The encoding used is invalid.");
            e.printStackTrace();
        }
        return hashcode;
    }
}
