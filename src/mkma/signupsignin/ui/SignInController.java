/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import user_message.Message;
import user_message.User;

/**
 *
 * @author Martin Gros
 */
public class SignInController implements Initializable {

    @FXML
    private Stage stage;

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignUp;

    @FXML
    private void handleButtonSignIn(ActionEvent event) {
        
        boolean error = false;
        
        if (this.txtUser.getText().trim().equals("")) {
            Alert alertEmpty = new Alert(Alert.AlertType.ERROR, "User and password fields"
                    + " are empty", ButtonType.OK);
            alertEmpty.showAndWait();
            error = true;
        }
        if (this.txtUser.getText().trim().length()<5){
            Alert alertShortUser = new Alert(Alert.AlertType.ERROR, "Username is"
                    + " too short", ButtonType.OK);
            alertShortUser.showAndWait();
            error = true;     
        }
        if (this.txtUser.getText().trim().length()>20){
            Alert alertlongUser = new Alert(Alert.AlertType.ERROR, "Username is"
                    + " too long", ButtonType.OK);
            alertlongUser.showAndWait();
            error = true;
        }
        if (!error) {
            User user = new User();
            user.setLogin(txtUser.getText().toString());
            user.setPassword(txtPass.getText().toString());
            Message message = new Message();
            message.setUser(user);
            message.setMessageType(1);
            
            sendMessage(message);
        } 
            
    }

    @FXML
    private void handleButtonSignUp(ActionEvent event) throws IOException {
      start_signup(stage);
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
        handleWindowShowing();
        txtUser.textProperty().addListener((this::textchanged));
        txtPass.textProperty().addListener((this::textchanged));
        stage.show();

    }

    private void handleWindowShowing() {
        btnSignIn.setDisable(true);
        btnSignUp.setTooltip(new Tooltip("Click to sign up!"));
        btnSignIn.setTooltip(new Tooltip("Click to log in!"));

    }

    private void textchanged(Observable obv) {

        if (this.txtUser.getText().trim().equals("") || this.txtPass.getText().trim().equals("")) {
            btnSignIn.setDisable(true);
        } else {
            btnSignIn.setDisable(false);
        }

    }

    private void start_signup(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/SignUp.fxml"));
        Parent root = (Parent) loader.load();

        SignUpController controller = (loader.getController());
        controller.setStage(primaryStage);
        controller.initStage(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSignIn.setDisable(true);
        
    }

    private void sendMessage(Message message) {
        Socket socket = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        try{
            socket = new Socket("localhost", 6302);
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(message);
        } catch (Exception e){
            System.out.println (e.getMessage());
        }finally {
            try {
                objectOutputStream.close();
            } catch (IOException ex) {
                System.out.println (ex.getMessage());
            }
            try {
                outputStream.close();
            } catch (IOException ex) {
                System.out.println (ex.getMessage());
            }
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println (ex.getMessage());
            }
        }
    }
    }


