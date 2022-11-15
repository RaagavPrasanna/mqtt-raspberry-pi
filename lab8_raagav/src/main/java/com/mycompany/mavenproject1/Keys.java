/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author raaga
 */
public class Keys {
    private KeyStore ks;
    private String storePath;
    
    // Constructor that takes as input KeyStore password and file path.
    public Keys(String psswd, String filepath) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        storePath = filepath;
        ks = KeyStore.getInstance(new File(filepath), psswd.toCharArray());
        System.out.println("Successfully loaded KeyStore");
    }
    
    public void storeKeyInKeyStore(String keyAlias, String keyPsswd, String storePass) throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey sk = keyGenerator.generateKey(); 
        
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(sk);
        
        KeyStore.ProtectionParameter entryPassword =  new KeyStore.PasswordProtection(keyPsswd.toCharArray());
        
        ks.setEntry(keyAlias, secretKeyEntry, entryPassword);
        
        storeKeyStore(storePass);
        System.out.println("Successfully added key");
    }
    
    public SecretKey getKey(String keyAlias, String psswd) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableEntryException {        
        SecretKey sk = (SecretKey)ks.getKey(keyAlias, psswd.toCharArray());
        System.out.println("Got key: " + sk);
        return sk;
    }
    
    private void storeKeyStore(String storePass) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException{
        try(FileOutputStream keyStoreOutputStream = new FileOutputStream(storePath)) {
            ks.store(keyStoreOutputStream, storePass.toCharArray());
        }
    }
}
