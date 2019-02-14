/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nlamei
 */
public class ScreensFramework extends Application{

    public static String screen0ID = "HomePage";
    public static String screen0File = "HomePage.fxml";
    public static String screen1ID = "LoginRegister";
    public static String screen1File = "LoginRegister.fxml";
    public static String screen2ID = "MainScreen";
    public static String screen2File = "MainScreen.fxml";
    public static String screen3ID = "PlayMode";
    public static String screen3File = "PlayMode.fxml";
    public static String screen4ID = "Gameboard";
    public static String screen4File = "Gameboard.fxml";
      
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.screen0ID, ScreensFramework.screen0File);
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
        mainContainer.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
        
        mainContainer.setScreen(ScreensFramework.screen0ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
