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
// import javafx.application.Application;
// import javafx.application.Platform;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
//import java.util.Scanner;


/**
 * JavaFX App
 */
public class App /*extends Application*/ {
    
    //Allows the stage be easily accessible
//    public static Stage theStage;
    public static Thread tempThread;
    public static Thread buzzerThread;
    public static Thread InfraredThread;
    public static KeyStore ks;
//    @Override
//    public void start(Stage stage) throws IOException {
//        var scene = new Scene(new FXScreen(), 1060, 910);
//        App.theStage = stage;
//        
//        //Set the active scene
//        theStage.setScene(scene);
//        theStage.show();
//        
//        // Make sure the application quits completely on close
//        theStage.setOnCloseRequest(t -> {
//            Platform.exit();
//            System.exit(0);
//        });
//    }

    public static void main(String[] args) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException{
        
        
        loadKeyStore("keystore123", "src\\main\\resources\\ProjectKeystore\\ECcertif.ks");
        storeKeyInKeyStore("raagav", "raagav123");
        getKey("raagav", "raagav123");
        //System.out.println("Keystore is fine");
        
//        boolean run = true;
//        Scanner reader = new Scanner(System.in);
//        CameraApp ca = new CameraApp();
//        while(run) {
//            System.out.println("Select choice");
//            String choice = reader.nextLine();
//            if(choice.equals("Close")) {
//                System.out.println("Setting run to false");
//                run = false;
//            } else if(choice.equals("Sensor")){
//                System.out.println("Calling sensor");
//                temperatureHumiditySensor();
//            } else if(choice.equals("Camera")) {
//                ca.callCamera();
//            } else if(choice.equals("Buzzer")) {
//                 // check is the thread is alive
//                 if(buzzerThread.isAlive()){
//                     buzzerThread.interrupt();
//                 }
//                 Buzzer();
//            } else if (choice.equals("Infrared")){
//                // check is the thread is alive
//                if(InfraredThread.isAlive()){
//                     InfraredThread.interrupt();
//                 }
//                InfraredMotionSensor();
//            }else {
//                System.out.println("Invalid choice");
//            }
//        }
//        System.out.println("Exit");
    }
    
    public static void loadKeyStore(String psswd, String path) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        ks = KeyStore.getInstance(new File(path), psswd.toCharArray());
        System.out.println("Successfully loaded KeyStore");
    }
    
    public static void storeKeyInKeyStore(String keyAlias, String keyPsswd) throws NoSuchAlgorithmException, KeyStoreException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey sk = keyGenerator.generateKey(); 
        
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(sk);
        
        KeyStore.ProtectionParameter entryPassword =  new KeyStore.PasswordProtection(keyPsswd.toCharArray());
        
        ks.setEntry(keyAlias, secretKeyEntry, entryPassword);
        System.out.println("Successfully added key");
    }
    
    public static void getKey(String keyAlias, String psswd) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableEntryException {
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(psswd.toCharArray());
        
        KeyStore.Entry keyEntry = ks.getEntry(keyAlias, entryPassword);
        System.out.println("Got key: " + keyEntry);
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
