package mkma.signupsignin.ui;

import exceptions.DataBaseConnectionException;
import exceptions.PassNotCorrectException;
import exceptions.ServerErrorException;
import exceptions.TimeOutException;
import exceptions.UserExistsException;
import exceptions.UserNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mkma.signupsignin.signable.SignableFactory;
import signable.Signable;
import user_message.User;

/**
 * This class is the controller for the sign-up window. It contains methods that
 * control it, like checks of the text fields or to define the buttons.
 *
 * @author Kerman Rodríguez
 */

public class SignUpController{

    /**
     * The stage that is going to be shown
     */
    @FXML
    private Stage stage;
    /**
     * Text field to enter the user
     */
    @FXML
    private TextField txtUser;
    /**
     * Text field to enter the password
     */
    @FXML
    private PasswordField txtPass;
    /**
     * Text field to repeat the password
     */
    @FXML
    private PasswordField txtPassAgain;
    /**
     * Text field to enter the email
     */
    @FXML
    private TextField txtEmail;
    /**
     * Text field to enter the name of the user
     */
    @FXML
    private TextField txtName;
    /**
     * Button to sign in the user
     */
    @FXML
    private Button btnSignUp;
    /**
     * Button to go back to the sign in window
     */
    @FXML
    private Button btnBack;

    /**
     * Method that runs when you click the sign-up button. It calls the
     * validation method and if valid, it sends a user to the implementation.
     * 
     * @param event it is the clicking event of the button
     */
    @FXML
    private void handleButtonSignUp(ActionEvent event) {
        boolean error = validate();
        String alertError = null;
        boolean alertNeeded = false;
        if (!error) {
            User user = new User();
            user.setLogin(txtUser.getText());
            user.setPassword(txtPass.getText());
            user.setEmail(txtEmail.getText());
            user.setFullName(txtName.getText());
            SignableFactory factory = new SignableFactory();
            Signable signable = factory.getSignable();
            try {
                User received = signable.signUp(user);
                btnSignUp.setText("Signed Up");
                btnSignUp.setDisable(true);
            } catch (DataBaseConnectionException | ServerErrorException | TimeOutException ex) {
                alertNeeded = true;
                alertError = "An unexpected error ocurred on the server.";
            } catch (UserExistsException ex) {
                alertNeeded = true;
                alertError = "The user you are trying to create already exists.";
            }

            if (alertNeeded) {
                Alert exceptionAlert = new Alert(AlertType.ERROR,
                        alertError, ButtonType.OK);
                exceptionAlert.showAndWait();
            }

        }
    }

    /**
     * Method that runs when you click the back button. It closes this window
     * and opens the Sign-in window.
     *
     * @param event it is the clicking event of the button
     * @throws IOException when there is an input/output error
     */
    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        //It gets the FXML of the sign-in window
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Parent root = (Parent) loader.load();
        //It creates a controller for the window and runs it
        SignInController controller = (loader.getController());
        controller.setStage(stage);
        controller.initStage(root);*/
        stage.close();
    }


    /**
     * Method to get the stage in order to use in in the window
     *
     * @return the stage needed
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Method used to set the stage to the window
     *
     * @param stage the stage needed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Method that initializes the window. It sets its properties and shows it
     *
     * @param root the parent of the window
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root, 691, 405);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Sign-up");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnShowing(this::handleWindowShowing);
        //It calls the method that handles how the elements show up
        stage.show();
    }

    /**
     * Method used to set various additions to the elements, like tooltip
     * buttons, or addition of listeners.
     */
    private void handleWindowShowing(WindowEvent event) {
        //It disables the signup button
        btnSignUp.setDisable(true);
        
        txtUser.setText("");
        txtPass.setText("");
        txtPassAgain.setText("");
        txtEmail.setText("");
        txtName.setText("");
        

        //It sets tooltips in the buttons and text fields, to tell the user about them
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

        //It sets listeners to the text fields with a method that checks if they are empty
        txtUser.textProperty().addListener(this::textChanged);
        txtPass.textProperty().addListener(this::textChanged);
        txtPassAgain.textProperty().addListener(this::textChanged);
        txtEmail.textProperty().addListener(this::textChanged);
        txtName.textProperty().addListener(this::textChanged);
    }

    /**
     * Method that validates the fields and returns a boolean. It runs various
     * checks on each and every field, and if they are all valid it returns a
     * true boolean.
     *
     * @return a boolean that tells if the fields have any errors.
     */
    private boolean validate() {
        //Creation of variables
        boolean error = false;
        String alertList = "";
        //Checks if the user is long enough
        if (txtUser.getText().length() < 5) {
            error = true;
            alertList = alertList.concat("The username is too short.\n");
        }
        //Checks if the user is too long
        if (txtUser.getText().length() > 20) {
            error = true;
            alertList = alertList.concat("The username is too long.\n");
        }
        //Checks if the password meets the requirements
        if (isValidPass(txtPass.getText()) == false) {
            error = true;
            alertList = alertList.concat("The password needs to contain at least an upper-case, lower-case and a number.\n");
        }
        //Checks if the password is too short
        if (txtPass.getText().length() < 5) {
            error = true;
            alertList = alertList.concat("The password is too short.\n");
        }
        //Checks if the password and its confirmation are the same
        if (!txtPass.getText().equals(txtPassAgain.getText())) {
            error = true;
            alertList = alertList.concat("The passwords don´t match.\n");
        }
        //Checks if the email has a valid format
        if (!isValidEmail(txtEmail.getText())) {
            error = true;
            alertList = alertList.concat("The email format is not valid.\n");
        }
        //Shows an alert with any errors there might have been
        if (error) {
            Alert listAllAlerts = new Alert(AlertType.ERROR,
                    alertList, ButtonType.OK);
            Button aceptar = (Button) listAllAlerts.getDialogPane().lookupButton(ButtonType.OK);
            aceptar.setId("Aceptar");
            listAllAlerts.showAndWait();
        }

        return error;
    }

    /**
     * This method receives a password and checks if it meets the requirements.
     *
     * @param s the password to check
     * @return a boolean telling if the password is valid
     */
    public static boolean isValidPass(String s) {
        boolean valid = true;
        //Checks if it has any numbers
        Pattern pNumber = Pattern.compile("[0-9]");
        Matcher m = pNumber.matcher(s);
        if (!m.find()) {
            valid = false;
        }
        //Checks if there are any upper-case letters
        Pattern pUpper = Pattern.compile("[A-Z]");
        m = pUpper.matcher(s);
        if (!m.find()) {
            valid = false;
        }
        //Checks if there are any lower-case letters
        Pattern pLower = Pattern.compile("[a-z]");
        m = pLower.matcher(s);
        if (!m.find()) {
            valid = false;
        }
        return valid;
    }

    /**
     * This method receives an email and checks if it is valid using a Regular
     * Expression
     *
     * @param email the email to check
     * @return a boolean telling if the email is valid
     */
    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    /**
     * This method observes any changes in the text fields and disables the
     * button if any are empty.
     *
     * @param observable it tells the method that the fields assigned need to be
     * observed
     */
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
