package com.filesharingsystem.service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecryptionService {
    private static final Logger logger = LoggerFactory.getLogger(DecryptionService.class);

    public byte[] decryptFile(byte[] fileData, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedFile = cipher.doFinal(fileData);
        logger.info("File has been decrypted !!!!!!!!!!!!!!!!!");
        return decryptedFile;
    }
}
