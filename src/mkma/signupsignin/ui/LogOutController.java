/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import exceptions.PassNotCorrectException;
import exceptions.ServerErrorException;
import exceptions.TimeOutException;
import exceptions.UserNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import mkma.signupsignin.signable.SignableFactory;
import signable.Signable;
import user_message.User;
/**
 *
 * @author 2dam
 */
public class LogOutController implements Initializable{

    
    @FXML
    private Stage stage;
    @FXML
    private Button btnCerrar;
    @FXML
    private TextField txtVent;
    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        //It gets the FXML of the sign-in window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/SignIn.fxml"));
        Parent root = (Parent) loader.load();
        //It creates a controller for the window and runs it
        SignInController controller = (loader.getController());
        controller.setStage(stage);
        controller.initStage(root);
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
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
