package com.mycompany.mavenproject1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.mavenproject1.InfraredMotionSensorProcessBuilder;

/**
 *
 * @author Tuan Danh Huynh
 */

/* Test class for ProcessBuilderEx class */
public class TemperatureAndHumidityProcessBuilderTest {
    
    public InfraredMotionSensorProcessBuilder processInstance;
    
    //Constructor for the test class
    public TemperatureAndHumidityProcessBuilderTest() {
        String pathAndFile = "src/main/Python/SebseLED.py";
        processInstance = new InfraredMotionSensorProcessBuilder(pathAndFile);
    } 
    
    /**
     * Test of startProcess method, of class TemperatureAndHumidityProcessBuilderTest.
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
