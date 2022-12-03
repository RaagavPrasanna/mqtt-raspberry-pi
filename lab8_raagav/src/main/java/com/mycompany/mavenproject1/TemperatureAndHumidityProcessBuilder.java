package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author Carlton Davis
 */

public class TemperatureAndHumidityProcessBuilder {
    
    //Stores the output from the process
    private String theOutput;
    
    private ProcessBuilder processBuilder;
    

    
   //The constructor to execute Python command takes a String
    public TemperatureAndHumidityProcessBuilder(String theApp) {
        this.processBuilder = new ProcessBuilder();
   
        //Determine if the OS is MS Windows 
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        
        //List to store the command and the command arguments
        List<String> commandAndArgs;
        
        //Setup the command based on the OS type
        if (isWindows) {
            commandAndArgs = List.of("C:\\Dev\\python3", theApp);
            this.processBuilder.command(commandAndArgs);
        }
        else {
            commandAndArgs = List.of("/usr/bin/python3", theApp);
            this.processBuilder.command(commandAndArgs);
        }
    }
    
    
    //Start the process and get the output
    String startProcess() throws IOException {
       
        //Initialize theOutput to null String
        this.theOutput = "";
        
        //Start the process
        var process = this.processBuilder.start();
        
        try (var reader = new BufferedReader(
            new InputStreamReader(process.getInputStream()))) {

            String line;

            while ((line = reader.readLine()) != null) {
                this.theOutput = this.theOutput + line;
            }

        }
        return this.theOutput;
    }
}
