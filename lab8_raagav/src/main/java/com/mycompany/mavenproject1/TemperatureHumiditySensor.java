package com.mycompany.mavenproject1;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

public class TemperatureHumiditySensor {
    private Thread tempThread;
    public TemperatureHumiditySensor(Thread thread){
        tempThread = thread;
    }

    public void startProcess(){      
        this.tempThread = new Thread(() -> {
            var pbdht11 = new TemperatureAndHumidityProcessBuilder("src/main/Python/DHT11.py");
                    
            String tempOutput;
            try {
                tempOutput = pbdht11.startProcess();
                System.out.println("Temperature: " +tempOutput.split(" ")[1]);
                System.out.println("Humidity: " +tempOutput.split(" ")[0]);
                        
            } catch(IOException ex) {
                Logger.getLogger(FXScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        tempThread.start();
    }
}
