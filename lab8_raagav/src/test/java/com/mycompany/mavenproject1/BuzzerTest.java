package com.mycompany.mavenproject1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.mavenproject1.Buzzer;

/**
 *
 * @author Tuan Danh Huynh
 */

/* Test class for ProcessBuilderEx class */
public class BuzzerTest {
    
    public Thread buzzerThread;
    
    //Constructor for the test class
    public BuzzerTest() {
        
    } 
    
    /**
     * Test of startProcess method, of class ProcessBuilderEx.
     * @throws java.lang.Exception
     */
    @Test
    public void testStartProcess() throws Exception {
        Buzzer buzzer = new Buzzer(buzzerThread);
        buzzer.startProcess();
        // if the buzzer thread is running, it will return true
        assertTrue(buzzerThread.isAlive());
    }
}
