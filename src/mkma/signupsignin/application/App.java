/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import mkma.signupsignin.ui.*;

/**
 *
 * @author 2dam
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/SignIn.fxml"));
       Parent root = (Parent) loader.load();
       
       SignInController controller = (loader.getController());
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
