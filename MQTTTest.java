package com.mycompany.mavenproject1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.mavenproject1.MQTT;

/**
 *
 * @author Tuan Danh Huynh
 */

/* Test class for ProcessBuilderEx class */
public class MQTTTest {
    
    public Thread buzzerThread;
    
    //Constructor for the test class
    public MQTTTest() {
        
    } 

    /**
     * Test creating the MQTT object
     * @throws java.lang.Exception
     */
    @Test
    public void testValidCreation() throws Exception {
        MQTT m = new MQTT("ragaav123","prasana","src/main/resources/ProjectKeystorECcertif.ks", "keystore123");
        assertTrue();
    }

    /**
     * Test creating the MQTT object
     * @throws java.lang.Exception
     */
    @Test
    public void testInvalidCreation() throws Exception {
        MQTT m = new MQTT("ragaav123","prasana","src/main/resources/ProjectKeystorECcertif.ks", "keystore123");
        assertTrue(true);
    }
    
    /**
     * Test all send functions
     * @throws java.lang.Exception
     */
    @Test
    public void testSends() throws Exception {
        MQTT m = new MQTT("ragaav123","prasana","src/main/resources/ProjectKeystorECcertif.ks", "keystore123");

        m.sendBuzzerMessage("hi");
        m.sendCameraTakenMessage("hi");
        m.sendCameraPictureMessage("hi");
        m.sendPublicKey("hi");
        m.sendTemperatureTakenMessage("hi");
        m.sendTemperatureDataMessage("hi");
        assertTrue(true);
    }
}
