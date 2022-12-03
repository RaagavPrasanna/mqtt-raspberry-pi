package com.mycompany.mavenproject1;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.Tile.ImageMask;
import eu.hansolo.tilesfx.Tile.TextSize;
import eu.hansolo.tilesfx.tools.Helper;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Tuan Danh Huynh
 */

/* Class to Create the GUI with the help of TilesFX library */
public class FXScreen extends HBox {

    //Flag to monitor the threads
    private static boolean running = true;
    private Tile gaugeTile;
    private Tile percentageTile;
    //Constructor 
    public FXScreen() throws IOException {
        //Start the example thread
        //this.TemperatureAndHimidityOutputThread();
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
        
        //Setup the tile to contain the TextField

        // gauge tile with temperature 
        gaugeTile = TileBuilder.create()
                .skinType(SkinType.GAUGE)
                .prefSize(350, 300)
                .title("Temperature")
                .unit("℃")
                .threshold(75)
                .build();

        // Tile with a clock
        var clockTile = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(350, 300)
                .title("Current time")
                .dateVisible(true)
                .locale(locale)
                .running(true)
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
//        String theCmd = "src/main/Python/helloWorld.py";
//
//        //ProcessBuilder object use to run the external command
//        var theProcessBuilder = new TemperatureAndHumidityProcessBuilder(theCmd);
//
//        //Get the output from the process
        String theOutput = "hhaha";


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
        textArea.setText("\n\nOutput from external program " + "\n" + theOutput);

        //Layout to contain the TextArea
        VBox textAreaVbox = new VBox(textArea);

        //Setup the tile for Buzzer
        var textAreaTileForBuzzerDanh = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Buzzer Message Danh")
                .graphic(textAreaVbox)
                .build();
        var textAreaTileForBuzzerAidan = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Buzzer message Aidan")
                .graphic(textAreaVbox)
                .build();
        var textAreaTileForBuzzerRaagav = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Buzzer message Raagav")
                .graphic(textAreaVbox)
                .build();
        // Setup the Tile for Temperature and Humidity
        var textAreaTileForTemperatureAndHumidityDanh = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Temperature and Humidity message Danh")
                .graphic(textAreaVbox)
                .build();
        var textAreaTileForTemperatureAndHumidityAidan = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Temperature and Humidity message Aidan")
                .graphic(textAreaVbox)
                .build();
        var textAreaTileForTemperatureAndHumidityRaagav = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Temperature and Humidity message Raagav")
                .graphic(textAreaVbox)
                .build();
        //set up the Tile for Motion Detector
            var textAreaTileForTMotionDetectorDanh = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Motion Detector message Raagav")
                .graphic(textAreaVbox)
                .build();
            var textAreaTileForTMotionDetectorAidan = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Motion Detector message Raagav")
                .graphic(textAreaVbox)
                .build();
            var textAreaTileForTMotionDetectorRaagav = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Motion Detector message Raagav")
                .graphic(textAreaVbox)
                .build();
                                

        //Add the tiles to VBoxes
        // display clock I and and IV A TextArea tile to display output from the Hello World python program
        var tilesColumn1 = new VBox(textAreaTileForTemperatureAndHumidityDanh, textAreaTileForBuzzerDanh, textAreaTileForTMotionDetectorDanh);
        tilesColumn1.setMinWidth(350);
        tilesColumn1.setSpacing(5);
        // display temperature II and V A tile similar to that in the example code for updating the program’s output.
        var tilesColumn2 = new VBox(textAreaTileForTemperatureAndHumidityAidan,textAreaTileForBuzzerAidan,textAreaTileForTMotionDetectorAidan);
        tilesColumn2.setMinWidth(350);
        tilesColumn2.setSpacing(5);
        //display A Percentage tile to display humidity III and VI A tile with an Exit button to quit the application
        var tilesColumn3 = new VBox(textAreaTileForTemperatureAndHumidityRaagav,textAreaTileForBuzzerRaagav, textAreaTileForTMotionDetectorRaagav);
        tilesColumn3.setMinWidth(350);
        tilesColumn3.setSpacing(5);

        //Add the VBoxes to the root layout, which is a HBox
        this.getChildren().add(tilesColumn1);
        this.getChildren().add(tilesColumn2);
        this.getChildren().add(tilesColumn3);
        this.setSpacing(5);
    }

    //Setup a thread  update temperature and himidity (threaded)
private void TemperatureAndHimidityOutputThread() {
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
        this.running = false;

        Platform.exit();
    }
}
