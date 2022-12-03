package com.mycompany.mavenproject1;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
import java.util.Scanner;
import com.hivemq.client.mqtt.mqtt5.exceptions.Mqtt5ConnAckException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * JavaFX App
 */
public class App /*extends Application*/ {
    
    //Allows the stage be easily accessible
//    public static Stage theStage;
    public static Thread tempThread;
    public static Thread buzzerThread;
    public static Thread InfraredThread;
//    @Override
//    public void start(Stage stage) throws IOException {
//        var scene = new Scene(new FXScreen(), 1400, 1200);
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

    public static void main(String[] args) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException, InvalidKeyException, SignatureException {
        boolean run = true;
        Scanner reader = new Scanner(System.in);
        CameraApp ca = new CameraApp();
        boolean invalidLogin = true; 
        String username = "";
        String password = "";
        Mqtt m = null;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        
        
        Keys k = new Keys("keystore123", "src/main/resources/ProjectKeystore/ECcertif.ks");
        while(invalidLogin) {
            try {
                System.out.println("Enter username");
                username = reader.nextLine();
                System.out.println("Enter password");
                password = reader.nextLine();
                
                m = new Mqtt(username,password, "src/main/resources/ProjectKeystore/ECcertif.ks", "keystore123");
                invalidLogin = false;
            } catch(Mqtt5ConnAckException me) {
                System.out.println("Invalid username or password");
            }
        }
 
        Buzzer buzzer = new Buzzer(buzzerThread);
        buzzer.startProcess();
        
        TemperatureHumiditySensor temp = new TemperatureHumiditySensor(tempThread);
        temp.startProcess();
        
        String choice = "";
        String currentTemp = "0";
        String currentHumid = "0";
        

        while(run) {
//            System.out.println("Select choice (Close, Sensor, Camera, Buzzer, Infrared, Key)");

            choice = reader.nextLine();
      
            // closing the app
            if(choice.equals("Close")) {
                System.out.println("Setting run to false");
                run = false;

            // calling the sensor
            } else if(!currentTemp.equals(temp.getTempState()) || !currentHumid.equals(temp.getHumidState())){
                System.out.println("Calling sensor");
                
                currentTemp = temp.getTempState();
                currentHumid = temp.getHumidState();
                
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                m.sendTemperatureTakenMessage("Temperature and Humidity Data taken at: " +sdf.format(ts));
                m.sendTemperatureDataMessage(currentTemp +"," +currentHumid);
                
            // calling the camera
            } else if(choice.equals("Camera")) {
                ca.callCamera();
                String imgData = ca.getRecentImageBytes();
                
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                m.sendCameraTakenMessage("Picture taken at: " +sdf.format(ts));
                m.sendCameraPictureMessage(imgData);
            // calling the buzzer
            } else if(buzzer.getBuzzer().getState().equals("buzzer turned on >>>")) {
                 
                 Timestamp ts = new Timestamp(System.currentTimeMillis());
                 
                 m.sendBuzzerMessage("Buzzer" +username +" pressed at: " +sdf.format(ts));
            // calling the Infrared Motion Sensor
            } else if (choice.equals("Infrared")){
                System.out.println("Calling Infrared Motion Sensor");
                InfraredMotionSensor infrared = new InfraredMotionSensor(InfraredThread);
                infrared.startProcess();

            } else if(choice.equals("Key")) {
                m.sendPublicKey();
            } 
//            else {
//                System.out.println("Invalid choice");
//            }
        }
//        launch();
    }
}
