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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import user_message.User;

/**
 *
 * @author 2dam
 */
public class SignUpController implements Initializable {
    
    @FXML
    private Stage stage;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtPassAgain;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnSignUp;
    @FXML
    private Button btnBack;
    
    @FXML
    private void handleButtonSignUp(ActionEvent event) {
        User user = new User();
        user.setLogin(txtUser.getText());
        user.setPassword(txtPass.getText());
        user.setEmail(txtEmail.getText());
        user.setFullName(txtName.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    

    public void initStage(Parent root) {
        Scene scene = new Scene(root, 691, 405);
        stage.setTitle("Sign-up");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnShowing(this::handleWindowShowing);
        txtUser.textProperty().addListener(this::textChanged);
        txtPass.textProperty().addListener(this::textChanged);
        txtPassAgain.textProperty().addListener(this::textChanged);
        txtEmail.textProperty().addListener(this::textChanged);
        txtName.textProperty().addListener(this::textChanged);
        stage.show();
    }
    
    private void handleWindowShowing (WindowEvent event) {
            btnSignUp.setDisable(true);      
    }
    
    private void textChanged(Observable observable) {
        
    }
    
    
    
}
