package com.mycompany.mavenproject1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.mavenproject1.TemperatureHumiditySensor;

/**
 *
 * @author Tuan Danh Huynh
 */

/* Test class for ProcessBuilderEx class */
public class TemperatureAndHumidityTest {
    
    public Thread tempThread;
    
    //Constructor for the test class
    public TemperatureAndHumidityTest() {
        
    } 
    
    /**
     * Test of startProcess method, of class ProcessBuilderEx.
     * @throws java.lang.Exception
     */
    @Test
    public void testStartProcess() throws Exception {
        TemperatureHumiditySensor temp = new TemperatureHumiditySensor(tempThread);
        temp.startProcess();
        // if the buzzer thread is running, it will return true
        assertTrue(temp.startProcess());
    }
}
