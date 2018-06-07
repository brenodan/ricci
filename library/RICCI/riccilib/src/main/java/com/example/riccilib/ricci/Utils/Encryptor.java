package com.example.riccilib.ricci.Utils;

/**
 * Created by brenocruz on 2/3/17.
 * Modified from @http://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
 */

import android.util.Log;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {

    private String key;
    private String initVector;
    private final String TAG = "Encryptor";
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getInitVector() {
        return initVector;
    }
    public void setInitVector(String initVector) {
        this.initVector = initVector;
    }

    public String encrypt(String value) {

        try {

            IvParameterSpec iv = new IvParameterSpec(getInitVector().getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(getKey().getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            String encrypted = new String (cipher.doFinal(value.getBytes()));
            Log.d(TAG, "encrypted string: " + encrypted);
            return encrypted;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String decrypt(String encrypted) {

        try {

            IvParameterSpec iv = new IvParameterSpec(getInitVector().getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(getKey().getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted.getBytes()));

            return new String(original);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Encryptor (String key, String initVector){

        setInitVector(getInitVector());
        setKey(key);
    }

}
