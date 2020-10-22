/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.application;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mkma.signupsignin.ui.*;

/**
 *
 * @author 2dam
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/SignUp.fxml"));
       Parent root = (Parent) loader.load();
       
       SignUpController controller = (loader.getController());
       controller.setStage(primaryStage);
       controller.initStage(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
