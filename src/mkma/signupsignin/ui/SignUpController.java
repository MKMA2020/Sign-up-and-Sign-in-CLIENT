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
public class SignUpController {

    /**
     * The stage that is going to be shown
     */
    @FXML
    private Stage stageSignUp;
    /**
     * Text field to enter the user
     */
    @FXML
    private TextField SignUpTxtUser;
    /**
     * Text field to enter the password
     */
    @FXML
    private PasswordField SignUpTxtPass;
    /**
     * Text field to repeat the password
     */
    @FXML
    private PasswordField SignUpTxtPassAgain;
    /**
     * Text field to enter the email
     */
    @FXML
    private TextField SignUpTxtEmail;
    /**
     * Text field to enter the name of the user
     */
    @FXML
    private TextField SignUpTxtName;
    /**
     * Button to sign in the user
     */
    @FXML
    private Button SignUpBtnSignUp;
    /**
     * Button to go back to the sign in window
     */
    @FXML
    private Button SignUpBtnBack;
    
    /**
     * Method will close SignUp window and open SignIn window.
     * @param event The event fired.
     */
    @FXML
    public void setOnCloseRequest(WindowEvent event) {
        Stage stageSignIn = new Stage();
        try {
            //It gets the FXML of the sign-in window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root = (Parent) loader.load();
            //It creates a controller for the window and runs it
            SignInController controller = (loader.getController());
            controller.setStageSignIn(stageSignIn);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method that runs when you click the sign-up button. It calls the
     * validation method and if valid, it sends a user to the implementation.
     *
     * @param event it is the clicking event of the button
     * @throws IOException when there is an input/output error
     */
    @FXML
    private void handleButtonSignUp(ActionEvent event) throws IOException {
        // Sign Up button clicked logger
        Logger.getLogger(SignUpController.class.getName()).log(Level.INFO, "SignUp Button Clicked");
        // Check if there are errors in the text fields
        boolean error = validate();
        String alertError = null;
        boolean alertNeeded = false;
        if (!error) {
            // Create user
            User user = new User();
            // Set data from text fields to user
            user.setLogin(SignUpTxtUser.getText());
            user.setPassword(SignUpTxtPass.getText());
            user.setEmail(SignUpTxtEmail.getText());
            user.setFullName(SignUpTxtName.getText());
            // Get a new signable Object
            SignableFactory factory = new SignableFactory();
            Signable signable = factory.getSignable();
            try {
                // Call signUp method on Signable
                User received = signable.signUp(user);
                // If no exception continue
                SignUpBtnSignUp.setText("Signed Up");
                SignUpBtnSignUp.setDisable(true);

// MODIFICACION DIN

                // Show Alert all Okay
                Alert exceptionAlert = new Alert(AlertType.CONFIRMATION,
                        "User succesfully created", ButtonType.OK);
                exceptionAlert.showAndWait();
                Stage stageSignIn = new Stage();
                // Get FXML of the sign-in window
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                Parent root = (Parent) loader.load();
                // Create a controller for the window and run
                SignInController controller = (loader.getController());
                controller.setStageSignIn(stageSignIn);
                controller.initStage(root);
                controller.getStageSignIn().show();
                stageSignUp.close();

// FIN MODIFICACION DIN

            } catch (DataBaseConnectionException | ServerErrorException | TimeOutException ex) {
                alertNeeded = true;
                // Message to be shown to user:
                alertError = "An unexpected error ocurred on the server.";
            } catch (UserExistsException ex) {
                alertNeeded = true;
                // Message to be shown to user:
                alertError = "The user you are trying to create already exists.";
            }
            // Show error alert
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
        Stage stageSignIn = new Stage();
        // Back button pressed logger
        Logger.getLogger(SignUpController.class
                .getName()).log(Level.INFO, "Back Button Clicked, Opening: SignInWindow");
        // Get FXML ofthe sign-in window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Parent root = (Parent) loader.load();
        // Create a controller for the window and run
        SignInController controller = (loader.getController());
        controller.setStageSignIn(stageSignIn);
        controller.initStage(root);
        stageSignUp.close();
    }

    /**
     * Method to get the stage in order to use in in the window
     *
     * @return SignUp Stage object.
     */
    public Stage getStageSignUp() {
        return stageSignUp;
    }

    /**
     * Method used to set the stage to the window
     *
     * @param stageSignUp the stage.
     */
    public void seStageSignUp(Stage stageSignUp) {
        this.stageSignUp = stageSignUp;
    }

    /**
     * Method that initializes the window. It sets its properties and shows it
     *
     * @param root the parent of the window
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root, 691, 405);
        stageSignUp.setTitle("Sign-up");
        stageSignUp.setResizable(false);
        stageSignUp.setScene(scene);
        // Set text fields empty
        SignUpTxtUser.setText("");
        SignUpTxtPass.setText("");
        SignUpTxtPassAgain.setText("");
        SignUpTxtEmail.setText("");
        SignUpTxtName.setText("");

        // Call method that handles how the elements show up
        handleWindowShowing();
        stageSignUp.show();
        stageSignUp.setOnCloseRequest(this::setOnCloseRequest);
    }

    /**
     * Method used to set various additions to the elements, like tooltip
     * buttons, or addition of listeners.
     */
    private void handleWindowShowing() {
        //It disables the signup button
        SignUpBtnSignUp.setDisable(true);

        //It sets tooltips in the buttons and text fields, to tell the user about them
        SignUpBtnSignUp.setTooltip(new Tooltip("Click to create an user "
                + "with this credentials"));
        SignUpBtnBack.setTooltip(new Tooltip("Click to go back "
                + "to the login"));
        SignUpTxtUser.setTooltip(new Tooltip("Between 5 and 20 characters"));
        SignUpTxtPass.setTooltip(new Tooltip("Contains lower-case, "
                + "upper-case and numbers"));
        SignUpTxtPassAgain.setTooltip(new Tooltip("Repeat password"));
        SignUpTxtEmail.setTooltip(new Tooltip("Valid format e-mail"));
        SignUpTxtName.setTooltip(new Tooltip("Write your name and surname"));

        //It sets listeners to the text fields with a method that checks if they are empty
        SignUpTxtUser.textProperty().addListener(this::textChanged);
        SignUpTxtPass.textProperty().addListener(this::textChanged);
        SignUpTxtPassAgain.textProperty().addListener(this::textChanged);
        SignUpTxtEmail.textProperty().addListener(this::textChanged);
        SignUpTxtName.textProperty().addListener(this::textChanged);
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
        if (SignUpTxtUser.getText().length() < 5) {
            error = true;
            alertList = alertList.concat("The username is too short.\n");
        }
        //Checks if the user is too long
        if (SignUpTxtUser.getText().length() > 20) {
            error = true;
            alertList = alertList.concat("The username is too long.\n");
        }
        //Checks if the password meets the requirements
        if (isValidPass(SignUpTxtPass.getText()) == false) {
            error = true;
            alertList = alertList.concat("The password has to contain\nat least an upper-case,\nlower-case and a number.\n");
        }
        //Checks if the password is too short 
        if (SignUpTxtPass.getText().length() < 5) {
            error = true;
            alertList = alertList.concat("The password is too short.\n");
        }
        //Checks if the password and its confirmation are the same
        if (!SignUpTxtPass.getText().equals(SignUpTxtPassAgain.getText())) {
            error = true;
            alertList = alertList.concat("The passwords don´t match.\n");
        }
        //Checks if the email has a valid format
        if (!isValidEmail(SignUpTxtEmail.getText())) {
            error = true;
            alertList = alertList.concat("The email format is not valid.\n");
        }
        // Show alert if any or more errors exist
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
        // If all buttons contain text
        if (SignUpTxtUser.getText().trim().equals("") || SignUpTxtPass.getText().trim().equals("")
                || SignUpTxtPassAgain.getText().trim().equals("") || SignUpTxtEmail.getText().trim().equals("")
                || SignUpTxtName.getText().trim().equals("")) {
            // Set SignUpBtnSignUp to enabled
            SignUpBtnSignUp.setDisable(true);
        } else {
            // Set SignUpBtnSignUp to disabled
            SignUpBtnSignUp.setDisable(false);
        }
    }
}