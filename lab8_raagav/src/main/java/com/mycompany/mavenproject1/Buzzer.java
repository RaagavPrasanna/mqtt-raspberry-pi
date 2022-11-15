package com.mycompany.mavenproject1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buzzer {
    private Thread buzzerThread;
    public Buzzer(Thread thread){
        buzzerThread = thread;
    }

    public boolean startProcess(){      
        this.buzzerThread = new Thread(() ->{
            String path = "src/main/Python/Doorbell.py";
            var buzzer = new BuzzerProcessBuilder(path);
            String buzzerString;
            try {
                buzzerString = buzzer.startProcess();
                System.out.println(buzzerString);        
            } catch(IOException ex) {
                Logger.getLogger(FXScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        buzzerThread.start();
        return true;
    }
}
