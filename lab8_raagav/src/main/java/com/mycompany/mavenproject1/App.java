package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
 import javafx.application.Application;
 import javafx.application.Platform;
 import javafx.scene.Scene;
 import javafx.stage.Stage;
import java.util.Scanner;


/**
 * JavaFX App
 */
public class App /*extends Application*/ {
    
    //Allows the stage be easily accessible
    public static Stage theStage;
    public static Thread tempThread;
    public static Thread buzzerThread;
    public static Thread InfraredThread;
    @Override
    public void start(Stage stage) throws IOException {
        var scene = new Scene(new FXScreen(), 1060, 910);
        App.theStage = stage;
        
        //Set the active scene
        theStage.setScene(scene);
        theStage.show();
        
        // Make sure the application quits completely on close
        theStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException {
                         
    }
    
    public static void InfraredMotionSensor(){
        InfraredThread = new Thread(() ->{
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
    
    public static void Buzzer(){
        buzzerThread = new Thread(() ->{
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
    }
    public static void temperatureHumiditySensor() {
        tempThread = new Thread(() -> {
            var pbdht11 = new ProcessBuilderEx("src/main/Python/DHT11.py");
                    
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
