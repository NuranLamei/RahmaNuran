/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author nlamei
 */


public class PlayModeController implements Initializable, ControlledScreen {

    /**
     * Initializes the controller class.
     */
    
    ScreensController myController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     public void setScreenParent(ScreensController screenParent){
     
         System.out.println("setting parent");
         myController = screenParent;
     
     }
    
}
