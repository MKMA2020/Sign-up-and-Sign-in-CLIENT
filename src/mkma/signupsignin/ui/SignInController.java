package mkma.signupsignin.ui;

import exceptions.DataBaseConnectionException;
import exceptions.PassNotCorrectException;
import exceptions.ServerErrorException;
import exceptions.TimeOutException;
import exceptions.UserNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
 * This class is the controller for the sign-in window. It has the methods
 * needed to launch the other windows, plus send the data as well.
 *
 * @author Martin Gros
 */
public class SignInController {

    /**
     * The stage itself
     */
    @FXML
    private Stage stage;
    /**
     * Textfield for the user
     */
    @FXML
    private TextField txtUser;
    /**
     * Textfield for the password
     */
    @FXML
    private PasswordField txtPass;
    /**
     * Button to sign in the user
     */
    @FXML
    private Button btnSignIn;
    /**
     * Button to go to the sign up
     */
    @FXML
    private Button btnSignUp;

    /**
     * This method gets launched whenever the user hits the login button In case
     * the username is either longer than 20 or shorther than 5 chars it will
     * send and alert. IF there is no error and everything goes through the
     * applicattions main window will be launched.
     * 
     * @param event current event.
     * @throws IOException when there are input/output errors
     */
    @FXML
    private void handleButtonSignIn(ActionEvent event) throws IOException {

        boolean error = false;
        String alertError = null;
        boolean alertNeeded = false;
        final Logger LOG = Logger.getLogger("mkma.signupsignin.ui.SignInController.java");
        LOG.log(Level.INFO, "Attempt to sign in");

        if (this.txtUser.getText().trim().length() < 5) {
            Alert alertShortUser = new Alert(Alert.AlertType.ERROR, "Username is"
                    + " too short", ButtonType.OK);
            Button okButton = (Button) alertShortUser.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("btnOkS");
            alertShortUser.showAndWait();
            error = true;
        }
        if (this.txtUser.getText().trim().length() > 20) {
            Alert alertlongUser = new Alert(Alert.AlertType.ERROR, "Username is"
                    + " too long", ButtonType.OK);
            Button okButton = (Button) alertlongUser.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("btnOkL");

            alertlongUser.showAndWait();
            error = true;
        }
        if (!error) {
            User user = new User();
            user.setLogin(txtUser.getText());
            user.setPassword(txtPass.getText());
            LOG.log(Level.INFO, "Petition created by: " + user.getLogin());
            SignableFactory factory = new SignableFactory();
            Signable signable = factory.getSignable();
            try {
                user = signable.signIn(user);
                openWindow(stage, user);
            } catch (UserNotFoundException | PassNotCorrectException ex) {
                alertNeeded = true;
                alertError = "User or password are incorrect.";
            } catch (DataBaseConnectionException | ServerErrorException | TimeOutException ex) {
                alertNeeded = true;
                alertError = "An unexpected error ocurred on the server.";
            }

            if (alertNeeded) {
                Alert exceptionAlert = new Alert(Alert.AlertType.ERROR,
                        alertError, ButtonType.OK);
                exceptionAlert.showAndWait();
            }

        }

    }

    /**
     * If the button is clicked the sign up window will be launched.
     *
     * @param event event used
     * @throws IOException when there are input/output errors.
     */

    @FXML
    private void handleButtonSignUp(ActionEvent event) throws IOException {
        start_signup(stage);
    }

    /**
     * Method that returns the stage
     *
     * @return returns the stage
     */
    public Stage getStage() {
        return stage;
    }
    /**
     * Method that sets the stage
     * @param stage Stage used
     */

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initializes and starts the window.
     *
     * @param root Parent of the window
     */

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sign In");
        handleWindowShowing();
        txtUser.textProperty().addListener((this::textchanged));
        txtPass.textProperty().addListener((this::textchanged));
        stage.show();

    }

    /**
     * When the window's first launched, sets the logIn button to disabled and
     * adds 2 tooltips.
     */

    private void handleWindowShowing() {
        txtUser.setText("");
        txtPass.setText("");
        btnSignIn.setDisable(true);
        btnSignUp.setTooltip(new Tooltip("Click to sign up!"));
        btnSignIn.setTooltip(new Tooltip("Click to log in!"));

    }

    /**
     * This method's always looking whether the user's typing in both fields in
     * order to enable or disable the log in button.
     *
     * @param obv parameter used to observe the text fields.
     */

    private void textchanged(Observable obv) {

        if (this.txtUser.getText().trim().equals("") || this.txtPass.getText().trim().equals("")) {
            btnSignIn.setDisable(true);
        } else {
            btnSignIn.setDisable(false);
        }

    }

    /**
     * Method to invoke the signup window
     *
     * @param primaryStage Main stage
     * @throws IOException IO issues
     */

    private void start_signup(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = (Parent) loader.load();
        SignUpController controller = (loader.getController());
        controller.setStage(primaryStage);
        controller.initStage(root);
    }

    /**
     * Launches the main window
     *
     * @param primaryStage Main stage
     * @param logOutStage Stage for the log out window
     * @param user user used
     * @throws IOException IO issue
     */
    private void openWindow(Stage logOutStage, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignOut.fxml"));
        Parent root = (Parent) loader.load();

        LogOutController controller = (loader.getController());
        controller.setUsername(user);
        controller.setStage(logOutStage);
        controller.initStage(root);
    }
}