/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;
import clientSide.Client;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author rahma
 */
public class LoginRegisterController implements Initializable,ControlledScreen {
   
  
  ScreensController myController;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */

  @FXML
  Button loginBtn;
  @FXML
  TextField loginEmailTxt;
  @FXML
  TextField loginPasswdTxt;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
    
    }
    
    @FXML
    public void gotoMainScreenScreen(ActionEvent event) throws IOException
  { 
        String userEmail = loginEmailTxt.getText();
          System.out.println(userEmail);
        String userPassword = loginPasswdTxt.getText();
         System.out.println(userPassword);

        Client myClient = new Client("192.168.1.143",4444);
        if(!myClient.connectToServer())
        {
            System.out.println("can't connect to server");
        }
        else
        {
            myClient.login(userEmail, userPassword);
            System.out.println("connected to server from loginregister");
            System.out.println("going to screen2"); 
            Parent nextView =FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene nextScene =new Scene (nextView);

            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(nextScene);
            window.show(); 

        }
  }
  
    
    @Override
    public void setScreenParent(ScreensController screenParent)
    {
        myController=screenParent;
    }
    
    public void gotoNextScreen2(ActionEvent event) throws IOException
  { 
    System.out.println("setting parent");
    myController.setScreen(ScreensFramework.screen2ID);
  }
}
