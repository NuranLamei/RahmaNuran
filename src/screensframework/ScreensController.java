/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author nlamei
 */
public class ScreensController extends StackPane{
    
    
    private HashMap<String,Node> myScreens = new HashMap();
    public ScreensController(){
        super();
    }
    
    public void addScreen(String name, Node screen)
    {
        System.out.println("screen added to hashmap");
        myScreens.put(name,screen);
    }
    
    public Node getScreen(String name)
    {
        System.out.println("screen begotten from hashmap");
        return myScreens.get(name);
    }  
    
    public boolean loadScreen(String name, String resource)
    {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent)myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen)myLoader.getController());
           myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
           return true;
        } catch (IOException ex) {
            System.out.println("screen loaded");
            Logger.getLogger(ScreensController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
    public boolean unloadScreen(String name)
    {
    
        if(myScreens.remove(name)==null){
            
            System.out.println("screen didn't exist");
        return false;
        }
        else{
            System.out.println("screen unloaded");
            return true;
        }
    
    }
    
    public boolean setScreen(final String name)
    {
        System.out.println("screensframework.ScreensController.setScreen()");
    if(myScreens.get(name)!=null)
    {
        System.out.println("screens are loaded! msg from set screen");
        final DoubleProperty opacity = opacityProperty();
        if(!getChildren().isEmpty()){
            System.out.println("first screen msg from set screen");
            Timeline fade = new Timeline (
            new KeyFrame (Duration.ZERO, new KeyValue(opacity,1.0)),
            new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t){
                    getChildren().remove(0);
                    getChildren().add(0, myScreens.get(name));
                    Timeline fadeIn = new Timeline( 
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity,0.0)),
                        new KeyFrame(new Duration(800),new KeyValue(opacity,1.0))
                    );
                    fadeIn.play();
                }
                    
            }, new KeyValue(opacity,0.0)));
            fade.play();
        }
        
        else{
            System.out.println("not first screen. msg from set screen");
            setOpacity(0.0);
            getChildren().add(myScreens.get(name));
            Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(opacity,0.0)),
                new KeyFrame(new Duration(2500), new KeyValue(opacity,1.0))                    
            );
            fadeIn.play();
        }
        return true;
    }
    else{
        System.out.println("screen hasn't benn loaded. msg from set screen");
        return false;
    
    }
    }

    
    
    
}
