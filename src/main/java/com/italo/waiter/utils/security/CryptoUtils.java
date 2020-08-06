package com.italo.waiter.utils.security;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Base64;

public class CryptoUtils {

    public static String encrypt(String plainMsg, String password){
        final String salt = KeyGenerators.string().generateKey();
        final byte[] saltBytes = salt.getBytes();
        final byte[] messageToEncrypt = Encryptors.stronger(password, salt).encrypt(plainMsg.getBytes());
        final byte[] payload = new byte[messageToEncrypt.length + saltBytes.length];
        System.arraycopy(messageToEncrypt, 0, payload, 0, messageToEncrypt.length);
        System.arraycopy(salt.getBytes(), 0, payload, messageToEncrypt.length, payload.length - messageToEncrypt.length);
        return Base64.getEncoder().encodeToString(payload);
    }

    public static String decrypt(String encryptedMsg, String password){
        final byte[] encryptedPayload = Base64.getDecoder().decode(encryptedMsg);
        final byte[] encryptedMessage = new byte[encryptedPayload.length - 16];
        final byte[] salt = new byte[16];
        System.arraycopy(encryptedPayload, 0, encryptedMessage, 0, encryptedMessage.length);
        System.arraycopy(encryptedPayload, encryptedMessage.length, salt, 0, salt.length);
        return new String(Encryptors.stronger(password, new String(salt)).decrypt(encryptedMessage));
    }
}
