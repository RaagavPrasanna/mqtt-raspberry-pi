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
            System.out.println("Select choice");
            String choice = reader.nextLine();
            if(choice.equals("Close")) {
                System.out.println("Setting run to false");
                run = false;
            } else if(choice.equals("Sensor")){
                System.out.println("Calling sensor");
                temperatureHumiditySensor();
            } else if(choice.equals("Camera")) {
                ca.callCamera();
            } else if(choice.equals("Buzzer")) {
                 Buzzer();
            }else {
                System.out.println("Invalid choice");
            }
        }
        System.out.println("Exit");
    }
    public static void Buzzer(){
        buzzerThread = new Thread(() ->{
            String path = "src/main/Python/Doorbell.py";
            var buzzer = new BuzzerProcessBuilder(path);
            String test;
            try {
                test = buzzer.startProcess();
                System.out.println(test);        
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