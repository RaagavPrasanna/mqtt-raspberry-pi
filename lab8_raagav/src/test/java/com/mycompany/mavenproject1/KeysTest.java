package com.mycompany.mavenproject1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.security.PrivateKey;

import javax.crypto.SecretKey;

import com.mycompany.mavenproject1.Keys;

/**
 *
 * @author Tuan Danh Huynh
 */

/* Test class for ProcessBuilderEx class */
public class KeysTest {
    
    public Thread buzzerThread;
    
    //Constructor for the test class
    public KeysTest() {
        
    }

    /**
     * Test creating a key
     * @throws java.lang.Exception
     */
    @Test
    public void testKey() throws Exception {
        Keys key = new Keys("keystore123", "src\\main\\resources\\ProjectKeystore\\ECcertif.ks");
        
        assertTrue(true);
    }
    
    /**
     * Test getting a key
     * @throws java.lang.Exception
     */
    @Test
    public void testGetKey() throws Exception {
        Keys key = new Keys("keystore123", "src\\main\\resources\\ProjectKeystore\\ECcertif.ks");
        
        // Verify that it returns a key
        assertTrue(key.getKey("raagav", "raagav123") instanceof SecretKey);
    }

    /**
     * Test storing a key
     * @throws java.lang.Exception
     */
    @Test
    public void testStoreKeyInKeyStore() throws Exception {
        Keys key = new Keys("keystore123", "src\\main\\resources\\ProjectKeystore\\ECcertif.ks");
        
        // Verify that it returns a key
        key.storeKeyInKeyStore("ragaav", "ragaav123", "keystore123");

        assertTrue(true);
    }
}
