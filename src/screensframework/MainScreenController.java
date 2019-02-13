/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author rahma
 */

public class MainScreenController implements Initializable ,  ControlledScreen{

    /**
     * Initializes the controller class.
     */
    
    ScreensController myController;
    
    @FXML
    private FlowPane usersPane;
      
    @FXML
    private Button newUserButton;
         
       
     public void addUserToUsersPane()
 {
   System.out.println("newUser added");
   Button newUser = new Button("userX ");
   usersPane.getChildren().add(0,newUser);
  
 
 }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
       @Override
     public void setScreenParent(ScreensController screenParent){
     
         System.out.println("setting parent");
         myController = screenParent;
     
     }
    
}
