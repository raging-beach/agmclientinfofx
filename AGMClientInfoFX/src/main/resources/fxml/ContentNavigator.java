/*

 */

package fxml;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**

 */
public class ContentNavigator { 
    
    private static MainStageController mainController;
    
    public static void setController(MainStageController controller){
        ContentNavigator.mainController = controller;
    }
    
    public static void loadContent(String fxmlFile){
        try {
            mainController.setContent(
                    (AnchorPane) FXMLLoader.load(ContentNavigator.class.getResource(fxmlFile))
            );
        } catch (IOException ex) {
            Logger.getLogger(ContentNavigator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
