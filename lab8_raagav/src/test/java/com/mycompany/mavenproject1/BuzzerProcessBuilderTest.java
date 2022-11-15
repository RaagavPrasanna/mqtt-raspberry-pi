package com.mycompany.mavenproject1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.mavenproject1.BuzzerProcessBuilder;

/**
 *
 * @author Carlton Davis and Raagav Prasanna
 */

/* Test class for ProcessBuilderEx class */
public class BuzzerProcessBuilderTest {
    
    public BuzzerProcessBuilder processInstance;
    
    //Constructor for the test class
    public BuzzerProcessBuilderTest() {
        String pathAndFile = "src/main/Python/Doorbell.py";
        processInstance = new BuzzerProcessBuilder(pathAndFile);
    } 
    
    /**
     * Test of startProcess method, of class BuzzerProcessBuilderTest.
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
