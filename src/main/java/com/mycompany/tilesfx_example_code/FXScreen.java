package com.mycompany.tilesfx_example_code;

import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.Tile.ImageMask;
import eu.hansolo.tilesfx.Tile.TextSize;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import eu.hansolo.tilesfx.tools.Helper;
import java.io.IOException;
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
 * @author Carlton Davis
 */

/* Class to Create the GUI with the help of TilesFX library */
public class FXScreen extends HBox {

    //Stores timestamp generated within the  exampleThread method
    private Date threadedTimeStamp;

    //TextArea that needs to be accessed in the exampleThread method
    private TextArea textAreaThreaded;

    //Flag to monitor the threads
    private static boolean running = true;

    //Constructor 
    public FXScreen() throws IOException {
        //Start the example thread
        this.starExampleThread();

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
        var textFieldTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .text("Enter something")
                .graphic(tfContainer)
                .roundedCorners(true)
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
        
        var gaugeTile = TileBuilder.create()
                .skinType(SkinType.GAUGE)
                .prefSize(350, 300)
                .title("Gauge Tile")
                .unit("C")
                .threshold(40)
                .build();
        
        var percentageTile = TileBuilder.create()
                .skinType(SkinType.GAUGE)
                .prefSize(350, 300)
                .title("Percentage Tile")
                .unit("percent")
                .threshold(100)
                .build();

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

        // Switch button tile
        var switchTile = TileBuilder.create()
                .skinType(SkinType.SWITCH)
                .prefSize(350, 300)
                .title("Demo switch")
                .textSize(TextSize.BIGGER)
                .roundedCorners(false)
                .build();

        switchTile.setOnSwitchReleased(e -> System.out.println("Switch pressed"));
        switchTile.setOnSwitchReleased(e -> System.out.println("Switch released"));

        /*Text tile to display output from external program */
        //The command to execute
        String helloCmd = "src/main/Python/helloWorld.py";
        String humCMD = "src/main/Python/humidity.py";
        String tempCMD = "src/main/Python/temperature.py";

        //ProcessBuilder object use to run the external command
        var helloProcessBuilder = new ProcessBuilderEx(helloCmd);
        var humProcessBuilder = new ProcessBuilderEx(humCMD);
        var tempProcessBuilder = new ProcessBuilderEx(tempCMD);

        //Get the output from the process
        String helloOutput = helloProcessBuilder.startProcess();
        String humOutput = humProcessBuilder.startProcess();
        String tempOutput = tempProcessBuilder.startProcess();

        //Generate a timestamp
        var timeStamp2 = new Date();

        //Setup the tile
        var textTileForOutput = TileBuilder.create()
                .skinType(SkinType.TEXT)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Output from external program to Text Tile")
                .description("Output from external program at" + "\n" + timeStamp2 + ":" + "\n" + helloOutput)
                .descriptionAlignment(Pos.CENTER_LEFT)
                .textVisible(true)
                .build();

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
        textArea.setText("\n\nOutput from external program at" + "\n" + timeStamp2 + ":" + "\n" + helloOutput);
        gaugeTile.setValue(Double.parseDouble(tempOutput));
        percentageTile.setValue(Double.parseDouble(humOutput));
        //Layout to contain the TextArea
        VBox textAreaVbox = new VBox(textArea);

        //Setup the tile
        var textAreaTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Output from external program to TextArea tile")
                .graphic(textAreaVbox)
                .build();

        /* Setup custom tile to contain Choicebox*/
        //Create ChoiceBox
        ChoiceBox choiceBox = new ChoiceBox();

        //Add the choices to the ChoiceBox
        choiceBox.getItems().add("Choice 1");
        choiceBox.getItems().add("Choice 2");
        choiceBox.getItems().add("Choice 3");

        //Label for the choiceBox
        Label cbLabel = new Label("Select your choice:  ");
        cbLabel.setTextFill(Color.WHITE);

        //Layout to contain the ChoiceBox
        HBox hbox = new HBox(choiceBox);

        //Layout to contain the label and the ChoiceBox
        FlowPane flowpane = new FlowPane(cbLabel, hbox);

        //Setup event handler for ChoiceBox
        choiceBox.setOnAction((event) -> {
            System.out.println("Your selection: " + choiceBox.getValue());
        });

        //Setup the ChoiceBox tile
        var choiceboxTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .graphic(flowpane)
                .build();

        //Setup a custom tile containing a TextArea to display timestamp (non-threaded)
        var nonThreadedTimeStamp = new Date();

        //Setup tile with TextArea to display output from external program
        TextArea textAreaNonThreaded = new TextArea();

        //Make the TextArea non editable
        textAreaNonThreaded.setEditable(false);

        //Change the background and the font color of the TextArea
        textAreaNonThreaded.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: white;"
                + "-fx-text-box-border: transparent;");

        //Write output to TextArea
        textAreaNonThreaded.setText("\n\nTimestamp: " + nonThreadedTimeStamp);

        //Layout to contain the TextArea
        VBox textAreaVboxNonThreaded = new VBox(textAreaNonThreaded);

        //Setup the tile
        var textAreaTileNonThreaded = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("TextArea Tile for non-threaded timestamp")
                .graphic(textAreaVboxNonThreaded)
                .build();

        //TextArea tile to display timestamp (threaded)
        //Yet another Text tile
        threadedTimeStamp = new Date();

        textAreaThreaded = new TextArea();

        //Make the TextArea non editable
        textAreaThreaded.setEditable(false);

        //Change the background and the font color of the TextArea
        textAreaThreaded.setStyle("-fx-control-inner-background: #2A2A2A; "
                + "-fx-text-inner-color: #1E90FF;"
                + "-fx-text-box-border: transparent;");

        //Write output to TextArea
        textAreaNonThreaded.setText("\n\nTimestamp: " + nonThreadedTimeStamp);

        //Layout to contain the TextArea
        VBox textAreaVboxThreaded = new VBox(textAreaThreaded);

        //Setup the tile
        var textAreaTileThreaded = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("TextArea Tile for threaded timestamp")
                .graphic(textAreaVboxThreaded)
                .build();

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
        //var tilesColumn1 = new VBox(clockTile, worldTile, imageTile, textAreaTileNonThreaded);
        //tilesColumn1.setMinWidth(350);
        //tilesColumn1.setSpacing(5);

        //var tilesColumn2 = new VBox(choiceboxTile, textTileForOutput, textFieldTile, textAreaTileThreaded);
        //tilesColumn2.setMinWidth(350);
        //tilesColumn2.setSpacing(5);

        //var tilesColumn3 = new VBox(updateOutputTile, textAreaTile, switchTile, exitTile);
        //tilesColumn3.setMinWidth(350);
        //tilesColumn3.setSpacing(5);
        
        FlowGridPane pane = new FlowGridPane(3, 2,clockTile, gaugeTile, percentageTile, textTileForOutput, updateOutputTile, exitTile);
        pane.setHgap(5);
        pane.setVgap(5);
        //Add the VBoxes to the root layout, which is a HBox
        this.getChildren().add(pane);
        this.setSpacing(5);
    }

    //Setup a thread using Lambda implementation to update timestamp (threaded)
    private void starExampleThread() {
        Thread exampleThread = new Thread(() -> {
            while (running) {
                try {
                    //Delay 5 seconds
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    System.err.println("exampleThread thread got interrupted");
                }

                //Needed to be to update an active node
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        threadedTimeStamp = new Date();
                        textAreaThreaded.setText("\n\nTimestamp: " + threadedTimeStamp);
                    }
                });
            }
        });

        //Start the thread
        exampleThread.start();
    }

    //Stop the threads and close the application
    private void endApplication() {
        this.running = false;

        Platform.exit();
    }
}
