/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class SignInController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonSignIn(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("aupa");
    }    

    public void setStage(Stage primaryStage) {
        System.out.println("aupa");
    }

    public void initStage(Parent root) {
        System.out.println("aupa");
    }
    
}