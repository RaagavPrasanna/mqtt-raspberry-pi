package com.mycompany.tilesfx_example_code;

import java.io.IOException;
import javafx.scene.Scene;

/**
 *
 * @author Carlton Davis
 */

//Helper class used to update the tiles
public class UpdateData {
    
    public void updateOutput() throws IOException {
        var updatedScene = new Scene(new FXScreen(), 1060, 910);
        App.theStage.setScene(updatedScene);
        App.theStage.show();
    }
    
}
