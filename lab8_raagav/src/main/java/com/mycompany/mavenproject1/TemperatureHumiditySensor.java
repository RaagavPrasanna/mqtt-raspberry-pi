package com.mycompany.mavenproject1;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

// Control a temperature and humidity sensor
public class TemperatureHumiditySensor {
    private Thread tempThread;
    
    private TemperatureAndHumidityProcessBuilder pbdht11;
//

    private String tempState;
    private String humidState;
    
    public TemperatureHumiditySensor(Thread thread){
        tempThread = thread;
        
        pbdht11 = new TemperatureAndHumidityProcessBuilder("src/main/Python/DHT11.py");
    }

    public String getTempState() {
        return tempState;
    }
    
    public String getHumidState() {
        return humidState;
    }
    
    // Start the sensor and output its recordings
    public boolean startProcess(){      
        tempState = "0";
        humidState = "0";
                
        this.tempThread = new Thread(() -> {
            String tempOutput;
            try {
                while(true) {
                tempOutput = pbdht11.startProcess();
                tempState = tempOutput.split(" ")[1];
                humidState = tempOutput.split(" ")[0];
//                System.out.println("Temperature: " +tempOutput.split(" ")[1]);
//                System.out.println("Humidity: " +tempOutput.split(" ")[0]);
                }           
            } catch(IOException ex) {
                Logger.getLogger(FXScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        tempThread.start();
        return true;
    }
}
