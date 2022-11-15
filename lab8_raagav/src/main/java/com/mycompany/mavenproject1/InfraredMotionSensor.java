package com.mycompany.mavenproject1;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

public class InfraredMotionSensor {
    private Thread InfraredThread;
    public InfraredMotionSensor(Thread thread){
        InfraredThread = thread;
    }

    public void startProcess(){      
        this.InfraredThread = new Thread(() ->{
            String path = "src/main/Python/SenseLED.py";
            var infrared = new InfraredMotionSensorProcessBuilder(path);
            String infraredString;
            try {
                infraredString = infrared.startProcess();
                System.out.println(infraredString);        
            } catch(IOException ex) {
                Logger.getLogger(FXScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        InfraredThread.start();
    }
}
