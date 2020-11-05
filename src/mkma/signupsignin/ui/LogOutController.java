/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import user_message.User;

/**
 * Class for the logout controller and its methods. If the user gets here a greeting
 * will be shown with his name on it.
 * @author Martin Gros
 */
public class LogOutController implements Initializable{

    
    @FXML
    private Stage stage;
    @FXML
    private Button btnClose;
    @FXML
    private Text txtWindow;
    @FXML
    /**
     * Whenever the user hits the back button it will send him back to the log in window.
     */
    
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
    /**
     * Initializes and launches the window.
     * @param root 
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.setResizable(false);        
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    /**
     * This method changes the text in the window to show the user's name.
     * @param user the user that logged in
     */
    public void setUsername (User user) {
        txtWindow.setText("Hello, "+user.getFullName());
    }
    
    
}
