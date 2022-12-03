package com.mycompany.mavenproject1;

import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.Tile.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javafx.application.Platform;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import eu.hansolo.tilesfx.*;

/**
 * @author Raagav Prasanna
 */

/* Class to Create the GUI with the help of TilesFX library */
public class FXScreen extends HBox {

    
    private Tile gaugeTile;
    private Tile percentageTile;
    private static boolean running = true;
    
    //Constructor 
    public FXScreen() throws IOException {
        this.startSensorThread();
        
        //Build the screen
        this.buildScreen();
    }

    //Build the screen
    private void buildScreen() throws IOException {



        // Define our local setting (used by the clock)
        var locale = new Locale("en", "CA");

        //Custom Tile containing a TextField
        Label input = new Label("Input");
        input.setTextFill(Color.WHITE);
        TextField textfield = new TextField();
        Button tfButton = new Button("Submit");
        Button clearButton = new Button("Clear");

        //Event handlers to get the input
        tfButton.setOnAction(e -> System.out.println("You entered: " + textfield.getText()));
        clearButton.setOnAction(e -> textfield.clear());

        //Layout to contain the TextField and Buttons
        GridPane tfContainer = new GridPane();

        //Add space between the columns of the GridPane
        tfContainer.setHgap(5);

        //Add space between the rows of the GridPane
        tfContainer.setVgap(5);

        //Add the nodes to the GridPane
        tfContainer.add(input, 1, 4);
        tfContainer.add(textfield, 2, 4);
        tfContainer.add(clearButton, 1, 5);
        tfContainer.add(tfButton, 3, 5);


        // LAB: A clock tile showing the current date and time
        var clockTile = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(350, 300)
                .title("Clock Tile")
                .dateVisible(true)
                .locale(locale)
                .running(true)
                .build();
        
        // LAB: A gauge tile to display temperature reading obtained from the DHT11 sensor
        gaugeTile = TileBuilder.create()
                       .skinType(SkinType.GAUGE)
                       .prefSize(350, 300)
                       .title("Gauge Tile")
                       .unit("Degrees Celcius")
                       .threshold(100)
                       .build();
        
        
        // LAB: A percentage tile to display humidity data obtained from the DHT11 sensor
        percentageTile = TileBuilder.create()
                        .skinType(SkinType.PERCENTAGE)
                        .prefSize(350, 300)
                        .title("Percentage Tile")
                        .unit("%")
                        .description("Humidity")
                        .maxValue(100)
                        .build();

        // Setup tile with update button to update output
        var updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            var updateDataObj = new UpdateData();
            try {
                updateDataObj.updateOutput();
            } catch (IOException ex) {
                Logger.getLogger(FXScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Generate a timestamp 
        var timeStamp = new Date();

        //A custom tile containing update button and a timestamp
        var updateOutputTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .title("Update the output")
                .textSize(TextSize.BIGGER)
                .text("Last update date and time: " + timeStamp)
                .textColor(Color.MIDNIGHTBLUE)
                .backgroundColor(Color.LIGHTBLUE)
                .titleColor(Color.BLUE)
                .graphic(updateButton)
                .roundedCorners(true)
                .build();

        /*Text tile to display output from external program */
        //The command to execute
        String theCmd = "src/main/Python/helloWorld.py";

        //ProcessBuilder object use to run the external command
        var theProcessBuilder = new TemperatureAndHumidityProcessBuilder(theCmd);

        //Get the output from the process
        String theOutput = theProcessBuilder.startProcess();

        //Generate a timestamp
        var timeStamp2 = new Date();

        //Setup tile with TextArea to display output from external program
        TextArea textArea = new TextArea();

        //Make the TextArea non editable
        textArea.setEditable(false);

        /*Change the background and the font color of the TextArea
           and make the border of the TextArea transparent
         */
        textArea.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");

        //Write output to TextArea
        textArea.setText("\n\nOutput from external program at" + "\n" + timeStamp2 + ":" + "\n" + theOutput);

        //Layout to contain the TextArea
        VBox textAreaVbox = new VBox(textArea);

        // LAB: A TextArea tile to display output from the Hello World python program
        var textAreaTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Output from external program to TextArea tile")
                .graphic(textAreaVbox)
                .build();

        //Label for the choiceBox
        Label cbLabel = new Label("Select your choice:  ");
        cbLabel.setTextFill(Color.WHITE);

        //Setup a tile with an exit button to end the application
        var exitButton = new Button("Exit");

        //Setup event handler for the exit button
        exitButton.setOnAction(e -> endApplication());

        //Setup the tile
        var exitTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Quit the application")
                .graphic(exitButton)
                .roundedCorners(false)
                .build();

        //Add the tiles to VBoxes
        var tilesColumn1 = new VBox(clockTile, gaugeTile);
        tilesColumn1.setMinWidth(350);
        tilesColumn1.setSpacing(5);

        var tilesColumn2 = new VBox(percentageTile, updateOutputTile);
        tilesColumn2.setMinWidth(350);
        tilesColumn2.setSpacing(5);

        var tilesColumn3 = new VBox(textAreaTile, exitTile);
        tilesColumn3.setMinWidth(350);
        tilesColumn3.setSpacing(5);

        //Add the VBoxes to the root layout, which is a HBox
        this.getChildren().addAll(tilesColumn1, tilesColumn2, tilesColumn3);
        this.setSpacing(5);
    }
    // HAVEN"T TEST YET
    private void startBuzzerThread() {
        Thread tempThread = new Thread(() -> {
           while(running) {
               try {
                   Thread.sleep(5000);
               } catch(InterruptedException ex) {
                   System.err.println("Sensor thread got interupted");
               }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    var buzzerProcess = new BuzzerProcessBuilder("src/main/Python/Doorbell.py");
                    String buzzerOutPutTimeStame;
                    try {
                        // buzzer OutPutTimeStame should have format like 2022-12-03 21:21:12
                        buzzerOutPutTimeStame = buzzerProcess.startProcess();
                        // MISSING ADD MESSAGE TO PAYLOAD

                        
                    } catch(IOException ex) {
                        Logger.getLogger(FXScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
           }
        });
        
        tempThread.start();
    }

    private void startSensorThread() {
        Thread tempThread = new Thread(() -> {
           while(running) {
               try {
                   Thread.sleep(5000);
               } catch(InterruptedException ex) {
                   System.err.println("Sensor thread got interupted");
               }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    var pbdht11 = new TemperatureAndHumidityProcessBuilder("src/main/Python/DHT11.py");
                    
                    String tempOutput;
                    try {
                        tempOutput = pbdht11.startProcess();
                        gaugeTile.setValue(Double.parseDouble(tempOutput.split(" ")[1]));
                        percentageTile.setValue(Double.parseDouble(tempOutput.split(" ")[0]));
                        
                    } catch(IOException ex) {
                        Logger.getLogger(FXScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
           }
        });
        
        tempThread.start();
    }
    
    
    //Stop the threads and close the application
    private void endApplication() {
        FXScreen.running = false;
        
        Platform.exit();
    }
    
    // Method to close application for unit test
    public void close() {
        endApplication();
    }
}
