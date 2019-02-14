/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    @FXML 
    Button SingleModeBtn;
    @FXML 
    Button MultiModeBtn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     public void setScreenParent(ScreensController screenParent){
     
         System.out.println("setting parent");
         myController = screenParent;
     
     }
     
     public void gotoMultiMode(ActionEvent event){
     
           Parent nextView = null;
        try {
            nextView = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PlayModeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Scene nextScene =new Scene (nextView);

            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(nextScene);
            window.show(); 

     }
    
     
      public void gotoSingleMode(ActionEvent event){
     
           Parent nextView = null;
        try {
            nextView = FXMLLoader.load(getClass().getResource("Gameboard.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PlayModeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Scene nextScene =new Scene (nextView);

            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(nextScene);
            window.show(); 

     }
}
