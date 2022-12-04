package com.mycompany.mavenproject1;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.Tile.ImageMask;
import eu.hansolo.tilesfx.Tile.TextSize;
import eu.hansolo.tilesfx.tools.Helper;
import java.io.File;
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
    public TextArea textAreaForBuzzerAidan;
    public TextArea textAreaForBuzzerDanh;
    public TextArea textAreaForBuzzerRaagav;
    public TextArea textAreaForTemperatureAndHumidityDanh;
    public TextArea textAreaForTemperatureAndHumidityAidan;
    public TextArea textAreaForTemperatureAndHumidityRaagav;
    public TextArea textAreaForMotionDetectorDanh;
    public TextArea textAreaForMotionDetectorAidan;
    public TextArea textAreaForMotionDetectorRaagav;
    public Tile ImageTileForCameraDanh;
    public Tile ImageTileForCameraRaagav;
    public Tile ImageTileForCameraAidan;
    
    
    
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



        // Generate a timestamp 
        var timeStamp = new Date();

        



        //Setup tile with TextArea to display output from external program
        textAreaForBuzzerAidan = new TextArea();
        textAreaForBuzzerDanh = new TextArea();
        textAreaForBuzzerRaagav = new TextArea();
        textAreaForTemperatureAndHumidityDanh = new TextArea();
        textAreaForTemperatureAndHumidityAidan= new TextArea();
        textAreaForTemperatureAndHumidityRaagav = new TextArea();
        textAreaForMotionDetectorDanh = new TextArea();
        textAreaForMotionDetectorAidan = new TextArea();
        textAreaForMotionDetectorRaagav = new TextArea();
            
        //Make the TextArea non editable
        textAreaForBuzzerDanh.setEditable(false);
        textAreaForBuzzerAidan.setEditable(false);
        textAreaForBuzzerRaagav.setEditable(false);
        textAreaForTemperatureAndHumidityDanh.setEditable(false);
        textAreaForTemperatureAndHumidityAidan.setEditable(false);
        textAreaForTemperatureAndHumidityRaagav.setEditable(false);
        textAreaForMotionDetectorDanh.setEditable(false);
        textAreaForMotionDetectorAidan.setEditable(false);
        textAreaForMotionDetectorRaagav.setEditable(false);
        /*Change the background and the font color of the TextArea
           and make the border of the TextArea transparent for all Text Area
         */
        textAreaForBuzzerDanh.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");
        textAreaForBuzzerAidan.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");
        textAreaForBuzzerRaagav.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");
        textAreaForTemperatureAndHumidityDanh.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");
        textAreaForTemperatureAndHumidityAidan.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");
        textAreaForTemperatureAndHumidityRaagav.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");
        textAreaForMotionDetectorAidan.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");
        textAreaForMotionDetectorRaagav.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");
        //Write output to TextArea TODO U CAN CHANGE THE TEXT AREA IN SET TEXT, SO UPDATE THE MESSAGE ALSO
        textAreaForMotionDetectorDanh.setText("\n\nOutput from external program 1" + "\n");
        textAreaForMotionDetectorRaagav.setText("EXAMPLE");
        //Layout to contain the TextArea For ALL TILES
        VBox textAreaForBuzzerDanhVbox = new VBox(textAreaForBuzzerDanh);
        VBox textAreaForBuzzerAidanVbox = new VBox(textAreaForBuzzerAidan);
        VBox textAreaForBuzzerRaagavVbox = new VBox(textAreaForBuzzerRaagav);
        VBox textAreaForTemperatureAndHumidityDanhVbox = new VBox(textAreaForTemperatureAndHumidityDanh);
        VBox textAreaForTemperatureAndHumidityAidanVbox = new VBox(textAreaForTemperatureAndHumidityAidan);
        VBox textAreaForTemperatureAndHumidityRaagavVbox = new VBox(textAreaForTemperatureAndHumidityRaagav);
        VBox textAreaForMotionDetectorDanhVBox = new VBox(textAreaForMotionDetectorDanh);
        VBox textAreaForMotionDetectorAidanVBox = new VBox(textAreaForMotionDetectorAidan);
        VBox textAreaForMotionDetectorRaagavVBox = new VBox(textAreaForMotionDetectorRaagav);       
                                                                
                                                        
                                                        
        //Setup the tile for Buzzer
        var textAreaTileForBuzzerDanh = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Buzzer Message Danh")
                .graphic(textAreaForBuzzerDanhVbox)
                .build();
        var textAreaTileForBuzzerAidan = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Buzzer message Aidan")
                .graphic(textAreaForBuzzerAidanVbox)
                .build();
        var textAreaTileForBuzzerRaagav = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Buzzer message Raagav")
                .graphic(textAreaForBuzzerRaagavVbox)
                .build();
        // Setup the Tile for Temperature and Humidity
        var textAreaTileForTemperatureAndHumidityDanh = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Temperature and Humidity message Danh")
                .graphic(textAreaForTemperatureAndHumidityDanhVbox)
                .build();
        var textAreaTileForTemperatureAndHumidityAidan = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Temperature and Humidity message Aidan")
                .graphic(textAreaForTemperatureAndHumidityAidanVbox)
                .build();
        var textAreaTileForTemperatureAndHumidityRaagav = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Temperature and Humidity message Raagav")
                .graphic(textAreaForTemperatureAndHumidityRaagavVbox)
                .build();
        //set up the Tile for Motion Detector
            var textAreaTileForMotionDetectorDanh = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Motion Detector message Danh")
                .graphic(textAreaForMotionDetectorDanhVBox)
                .build();
            var textAreaTileForMotionDetectorAidan = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Motion Detector message Aidan")
                .graphic(textAreaForMotionDetectorAidanVBox)
                .build();
            var textAreaTileForMotionDetectorRaagav = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Motion Detector message Raagav")
                .graphic(textAreaForMotionDetectorRaagavVBox)
                .build();
        //set up the Tile for Image
        
        
        ImageTileForCameraDanh = TileBuilder.create()
                .skinType(SkinType.IMAGE)
                .prefSize(350, 300)
                // TODO missing image URL
//                .image(new Image(FXScreen.class.getResourceAsStream("sunny-clip-art.png")))
                .title("Danh Image Camera")
                .build();
        ImageTileForCameraAidan = TileBuilder.create()
                .skinType(SkinType.IMAGE)
                .prefSize(350, 300)
                // TODO missing image URL
//                .image()
                .title("Aidan Image Camera")
                .build();
        ImageTileForCameraRaagav = TileBuilder.create()
                .skinType(SkinType.IMAGE)
                .prefSize(350, 300)
                // TODO missing image URL
//               .image()
                .title("Raagav Image Camera")
                .build();
        
        
        
        //Add the tiles to VBoxes
        // display clock I and and IV A TextArea tile to display output from the Hello World python program
        var tilesColumn1 = new VBox(textAreaTileForTemperatureAndHumidityDanh,ImageTileForCameraDanh, textAreaTileForBuzzerDanh, textAreaTileForMotionDetectorDanh);
        tilesColumn1.setMinWidth(350);
        tilesColumn1.setSpacing(5);
        // display temperature II and V A tile similar to that in the example code for updating the program’s output.
        var tilesColumn2 = new VBox(textAreaTileForTemperatureAndHumidityAidan,ImageTileForCameraAidan,textAreaTileForBuzzerAidan,textAreaTileForMotionDetectorAidan);
        tilesColumn2.setMinWidth(350);
        tilesColumn2.setSpacing(5);
        //display A Percentage tile to display humidity III and VI A tile with an Exit button to quit the application
        var tilesColumn3 = new VBox(textAreaTileForTemperatureAndHumidityRaagav,ImageTileForCameraRaagav,textAreaTileForBuzzerRaagav, textAreaTileForMotionDetectorRaagav);
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
