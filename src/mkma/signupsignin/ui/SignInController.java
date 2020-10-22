/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 *
 * @author 2dam
 */
public class SignInController implements Initializable {
    
    @FXML
    private Stage stage;
    
    @FXML
    private TextField txtUser;
    @FXML
    private Text lblTitle;
    @FXML    
    private PasswordField txtPass;
    @FXML 
    private Button btnSignIn;
    @FXML 
    private Button btnSignUp;   
    

    
    @FXML
    private void handleButtonSignIn(ActionEvent event) {
        if (this.txtUser.getText().trim().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Los campos usuario y contraseña"
                    + " deben contener algo", ButtonType.OK);
            alert.showAndWait();
        }
        System.out.println("You clicked me!");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
        

  

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        txtUser.textProperty().addListener(this::textChanged);
        stage.show();
        
        
    }
    
    private void handleWindowShowing(WindowEvent event){
        btnSignIn.setDisable(true);
        
    }
    private void handleWindowShowing(Observable observable){
        btnSignIn.setDisable(true);}
    
    
}