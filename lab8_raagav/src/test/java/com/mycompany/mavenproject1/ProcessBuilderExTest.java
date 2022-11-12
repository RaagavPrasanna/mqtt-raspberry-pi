package com.mycompany.mavenproject1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carlton Davis and Raagav Prasanna
 */

/* Test class for ProcessBuilderEx class */
public class ProcessBuilderExTest {
    
    public ProcessBuilderEx processInstance;
    
    //Constructor for the test class
    public ProcessBuilderExTest() {
        String pathAndFile = "src/main/Python/helloWorld.py";
        processInstance = new ProcessBuilderEx(pathAndFile);
    } 
    
    /**
     * Test of startProcess method, of class ProcessBuilderEx.
     * @throws java.lang.Exception
     */
    @Test
    public void testStartProcess() throws Exception {
        System.out.println("startProcess");
        
        //Determine whether the process input stream is null
        String expResult = processInstance.startProcess();
        assertNotNull(expResult);
        
        //This assertion will only be executed if the previous assertion is valid
        assertTrue(expResult.contains("Hello World"));
    }
    
    /**
     * Tests if the FXScreen builds properly and the temperature and humidity thread is launched
     * If an exception is thrown the test fails
     */
    // @Test
    // public void testSensorThread() {
    //     try  {
    //         FXScreen fx = new FXScreen();
    //         fx.close();
    //     } catch(Exception e) {
    //         fail("Exception was thrown in sensor initialization");    
    //     }
        
    // }
}
