package com.mycompany.mavenproject1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.mavenproject1.InfraredMotionSensorProcessBuilder;

/**
 *
 * @author Carlton Davis and Raagav Prasanna
 */

/* Test class for ProcessBuilderEx class */
public class InfraredMotionSensorProcessBuilderTest {
    
    public InfraredMotionSensorProcessBuilder processInstance;
    
    //Constructor for the test class
    public InfraredMotionSensorProcessBuilderTest() {
        String pathAndFile = "src/main/Python/SebseLED.py";
        processInstance = new InfraredMotionSensorProcessBuilder(pathAndFile);
    } 
    
    /**
     * Test of startProcess method, of class InfraredMotionSensorProcessBuilderTest.
     * @throws java.lang.Exception
     */
    @Test
    public void testStartProcess() throws Exception {
        System.out.println("startProcess");
        
        //Determine whether the process input stream is null
        String expResult = processInstance.startProcess();
        assertNotNull(expResult);
    }
}
