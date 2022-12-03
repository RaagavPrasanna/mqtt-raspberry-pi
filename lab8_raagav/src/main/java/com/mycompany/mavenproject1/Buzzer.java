package com.mycompany.mavenproject1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buzzer {
    private Thread buzzerThread;
    private BuzzerProcessBuilder buzzer;
    
    public Buzzer(Thread thread){
        buzzerThread = thread;
        String path = "src/main/Python/Doorbell.py";
        buzzer = new BuzzerProcessBuilder(path);
    }

    public BuzzerProcessBuilder getBuzzer() {
        return this.buzzer;
    }
    
    public boolean startProcess(){      
        this.buzzerThread = new Thread(() ->{
            
            String buzzerString;
            try {
//                System.out.println("Reached here");
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
