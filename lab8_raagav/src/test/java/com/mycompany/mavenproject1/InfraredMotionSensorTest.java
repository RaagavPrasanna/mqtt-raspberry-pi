package com.mycompany.mavenproject1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.mavenproject1.InfraredMotionSensor;

/**
 *
 * @author Tuan Danh Huynh
 */

/* Test class for ProcessBuilderEx class */
public class InfraredMotionSensorTest {
    
    public Thread InfraredThread;
    
    //Constructor for the test class
    public InfraredMotionSensorTest() {
        
    } 
    
    /**
     * Test of startProcess method, of class ProcessBuilderEx.
     * @throws java.lang.Exception
     */
    @Test
    public void testStartProcess() throws Exception {
        InfraredMotionSensor infrared = new InfraredMotionSensor(InfraredThread);
        infrared.startProcess();
        // if the Infrared Motion Sensor thread is running, it will return true
        assertTrue(InfraredThread.isAlive());
    }
}
