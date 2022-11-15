package com.mycompany.mavenproject1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;


/**
 * JavaFX App
 */
public class App extends Application {
    
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

    public static void main(String[] args) {
        boolean run = true;
        Scanner reader = new Scanner(System.in);
        CameraApp ca = new CameraApp();
        while(run) {
            System.out.println("Select choice (Close, Sensor, Camera, Buzzer, Infrared)");
            String choice = reader.nextLine();
            // closing the app
            if(choice.equals("Close")) {
                System.out.println("Setting run to false");
                run = false;

            // calling the sensor
            } else if(choice.equals("Sensor")){
                System.out.println("Calling sensor");
                TemperatureHumiditySensor temp = new TemperatureHumiditySensor(tempThread);
                temp.startProcess();

            // calling the camera
            } else if(choice.equals("Camera")) {
                ca.callCamera();

            // calling the buzzer
            } else if(choice.equals("Buzzer")) {
                 System.out.println("Calling buzzer");
                 Buzzer buzzer = new Buzzer(buzzerThread);
                 buzzer.startProcess();
            
            // calling the Infrared Motion Sensor
            } else if (choice.equals("Infrared")){
                System.out.println("Calling Infrared Motion Sensor");
                InfraredMotionSensor infrared = new InfraredMotionSensor(InfraredThread);
                infrared.startProcess();

            }else {
                System.out.println("Invalid choice");
            }
        }
        System.out.println("Exit");
    }
}
