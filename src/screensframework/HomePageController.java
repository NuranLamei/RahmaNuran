/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rahma
 */
public class HomePageController implements Initializable,ControlledScreen {
    @FXML
    private Button startbtn;
    @FXML
    private Label label;
  public void gotoNextScreen(ActionEvent event) throws IOException
  { System.out.println("on my way to screen1");
      Parent nextView =FXMLLoader.load(getClass().getResource("LoginRegister.fxml"));
    Scene nextScene =new Scene (nextView);
    
    Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(nextScene);
    window.show();
  
  }

    /**
     * Initializes the controller class.
     */
  
  ScreensController myController;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenParent(ScreensController screenParent) {
      System.out.println("setting parent");
      myController = screenParent;
    }
    
    public void goToScreen1 (){
    
        
    
    }
    
}
