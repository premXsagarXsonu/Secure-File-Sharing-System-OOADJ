package com.filesharingsystem.service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptionService {
    private static final Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    public byte[] encryptFile(byte[] fileData, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedFile = cipher.doFinal(fileData);
        logger.info("File has been encrypted !!!!!!!!!!!!!!!!!");
        return encryptedFile;
    }
}
