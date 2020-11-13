package mkma.signupsignin.ui;

import exceptions.DataBaseConnectionException;
import exceptions.ServerErrorException;
import exceptions.TimeOutException;
import exceptions.UserExistsException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
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
import mkma.signupsignin.signable.SignableFactory;
import signable.Signable;
import user_message.User;

/**
 * This class is the controller for the sign-up window. It contains methods that
 * control it, like checks of the text fields or to define the buttons.
 *
 * @author Kerman Rodríguez
 */
public class SignUpController {

    /**
     * The stage that is going to be shown
     */
    @FXML
    private Stage stage;
    /**
     * Text field to enter the user
     */
    @FXML
    private TextField txtUserRegister;
    /**
     * Text field to enter the password
     */
    @FXML
    private PasswordField txtPassRegister;
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
    private Button btnSignUpRegister;
    /**
     * Button to go back to the sign in window
     */
    @FXML
    private Button btnBack;
    /**
     * The logger for the sign up
     */
    static final Logger LOG = Logger.getLogger("mkma.signupsignin.ui.SignUpController.java");

    /**
     * Method that runs when you click the sign-up button. It calls the
     * validation method and if valid, it sends a user to the implementation.
     *
     * @param event it is the clicking event of the button
     */
    @FXML
    private void handleButtonSignUp(ActionEvent event) {
        LOG.log(Level.INFO, "Sign up button clicked");
        boolean error = validate();
        String alertError = null;
        boolean alertNeeded = false;
        if (!error) {
            LOG.log(Level.INFO, "Sign up attempt by user " + txtUserRegister.getText());
            User user = new User();
            user.setLogin(txtUserRegister.getText());
            user.setPassword(txtPassRegister.getText());
            user.setEmail(txtEmail.getText());
            user.setFullName(txtName.getText());
            SignableFactory factory = new SignableFactory();
            Signable signable = factory.getSignable();
            try {
                User received = signable.signUp(user);
                LOG.log(Level.INFO, "User " + user.getLogin() + " registered.");
                btnSignUpRegister.setText("Signed Up");
                btnSignUpRegister.setDisable(true);
            } catch (DataBaseConnectionException | ServerErrorException | TimeOutException ex) {
                LOG.log(Level.INFO, "Server exception catched.");
                alertNeeded = true;
                alertError = "An unexpected error ocurred on the server.";
            } catch (UserExistsException ex) {
                LOG.log(Level.INFO, "User already exists");
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
        LOG.log(Level.INFO, "Going back to sign in.");
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
        LOG.log(Level.INFO, "Entering sign-up window");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Sign-up");
        stage.setResizable(false);
        stage.setScene(scene);
        //It sets listeners to the text fields with a method that checks if they are empty
        txtUserRegister.textProperty().addListener(this::textChanged);
        txtPassRegister.textProperty().addListener(this::textChanged);
        txtPassAgain.textProperty().addListener(this::textChanged);
        txtEmail.textProperty().addListener(this::textChanged);
        txtName.textProperty().addListener(this::textChanged);
        //It sets the method that controls the showing of the window
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
    }

    /**
     * Method used to set various additions to the elements, like tooltip
     * buttons, or addition of listeners.
     *
     * @param event the event executed
     */
    private void handleWindowShowing(Event event) {
        //It disables the signup button
        btnSignUpRegister.setDisable(true);
        //It resets the content of the text fields
        txtUserRegister.setText("");
        txtPassRegister.setText("");
        txtPassAgain.setText("");
        txtEmail.setText("");
        txtName.setText("");

        //It sets tooltips in the buttons and text fields, to tell the user about them
        btnSignUpRegister.setTooltip(new Tooltip("Click to create an user "
                + "with this credentials"));
        btnBack.setTooltip(new Tooltip("Click to go back "
                + "to the login"));
        txtUserRegister.setTooltip(new Tooltip("Between 5 and 20 characters"));
        txtPassRegister.setTooltip(new Tooltip("Contains lower-case, "
                + "upper-case and numbers"));
        txtPassAgain.setTooltip(new Tooltip("Repeat password"));
        txtEmail.setTooltip(new Tooltip("Valid format e-mail"));
        txtName.setTooltip(new Tooltip("Write your name and surname"));
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
        if (txtUserRegister.getText().length() < 5) {
            error = true;
            alertList = alertList.concat("The username is too short.\n");
        }
        //Checks if the user is too long
        if (txtUserRegister.getText().length() > 20) {
            error = true;
            alertList = alertList.concat("The username is too long.\n");
        }
        //Checks if the password meets the requirements
        if (isValidPass(txtPassRegister.getText()) == false) {
            error = true;
            alertList = alertList.concat("The password needs to contain at least an upper-case, lower-case and a number.\n");
        }
        //Checks if the password is too short
        if (txtPassRegister.getText().length() < 5) {
            error = true;
            alertList = alertList.concat("The password is too short.\n");
        }
        //Checks if the password and its confirmation are the same
        if (!txtPassRegister.getText().equals(txtPassAgain.getText())) {
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
        if (txtUserRegister.getText().trim().equals("") || txtPassRegister.getText().trim().equals("")
                || txtPassAgain.getText().trim().equals("") || txtEmail.getText().trim().equals("")
                || txtName.getText().trim().equals("")) {
            btnSignUpRegister.setDisable(true);
        } else {
            btnSignUpRegister.setDisable(false);
        }
    }
}
