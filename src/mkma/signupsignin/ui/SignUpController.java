/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import user_message.User;

/**
 *
 * @author Kerman Rodríguez
 */
public class SignUpController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private PasswordField txtPassAgain;
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
        boolean error = validate();
        if (!error) {
            User user = new User();
            user.setLogin(txtUser.getText());
            user.setPassword(txtPass.getText());
            user.setEmail(txtEmail.getText());
            user.setFullName(txtName.getText());
            System.out.println("gg bro");
        } else {
            System.out.println("fallo :(");
        }
    }

    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/SignIn.fxml"));
        Parent root = (Parent) loader.load();

        SignInController controller = (loader.getController());
        controller.setStage(stage);
        controller.initStage(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnSignUp.setDisable(true);
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
        handleWindowShowing();
        stage.show();
    }

    private void handleWindowShowing() {
        btnSignUp.setDisable(true);

        btnSignUp.setTooltip(new Tooltip("Click to create an user "
                + "with this credentials"));
        btnBack.setTooltip(new Tooltip("Click to go back "
                + "to the login"));
        txtUser.setTooltip(new Tooltip("Between 5 and 20 characters"));
        txtPass.setTooltip(new Tooltip("Contains lower-case, "
                + "upper-case and numbers"));
        txtPassAgain.setTooltip(new Tooltip("Repeat password"));
        txtEmail.setTooltip(new Tooltip("Valid format e-mail"));
        txtName.setTooltip(new Tooltip("Write your name and surname"));

        txtUser.textProperty().addListener(this::textChanged);
        txtPass.textProperty().addListener(this::textChanged);
        txtPassAgain.textProperty().addListener(this::textChanged);
        txtEmail.textProperty().addListener(this::textChanged);
        txtName.textProperty().addListener(this::textChanged);
    }

    private boolean validate() {
        boolean error = false;
        String alertList = "";

        if (txtUser.getText().length() < 5) {
            error = true;
            alertList = alertList.concat("The username is too short.\n");
        }

        if (txtUser.getText().length() > 20) {
            error = true;
            alertList = alertList.concat("The username is too long.\n");
        }

        if (isValidPass(txtPass.getText()) == false) {
            error = true;
            alertList = alertList.concat("The password needs to contain at least an upper-case, lower-case and a number.\n");
        }

        if (txtPass.getText().length() < 5) {
            error = true;
            alertList = alertList.concat("The password is too short.\n");
        }

        if (!txtPass.getText().equals(txtPassAgain.getText())) {
            error = true;
            alertList = alertList.concat("The passwords don´t match.\n");
        }

        if (!isValidEmail(txtEmail.getText())) {
            error = true;
            alertList = alertList.concat("The email format is not valid.\n");
        }
        if (error) {
            Alert listAllAlerts = new Alert(AlertType.ERROR,
                    alertList, ButtonType.OK);
            listAllAlerts.showAndWait();
        }

        return error;
    }

    public static boolean isValidPass(String s) {
        boolean valid = true;
        Pattern pNumber = Pattern.compile("[0-9]");
        Matcher m = pNumber.matcher(s);
        if (!m.find()) {
            valid = false;
        }
        Pattern pUpper = Pattern.compile("[A-Z]");
        m = pUpper.matcher(s);
        if (!m.find()) {
            valid = false;
        }
        Pattern pLower = Pattern.compile("[a-z]");
        m = pLower.matcher(s);
        if (!m.find()) {
            valid = false;
        }
        return valid;
    }

    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private void textChanged(Observable observable) {
        if (txtUser.getText().trim().equals("") || txtPass.getText().trim().equals("")
                || txtPassAgain.getText().trim().equals("") || txtEmail.getText().trim().equals("")
                || txtName.getText().trim().equals("")) {
            btnSignUp.setDisable(true);
        } else {
            btnSignUp.setDisable(false);
        }
    }

}
