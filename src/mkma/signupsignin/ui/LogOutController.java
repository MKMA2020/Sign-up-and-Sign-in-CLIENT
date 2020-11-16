package mkma.signupsignin.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import user_message.User;

/**
 * Class for the logout controller and its methods. If the user gets here a
 * greeting will be shown with his name on it.
 *
 * @author Martin Gros
 */
public class LogOutController {

    @FXML
    private Stage stage;
    @FXML
    private Button btnClose;
    @FXML
    private Text txtWindow;
    /**
     * Logger for the sign out window
     */
    static final Logger LOG = Logger.getLogger("mkma.signupsignin.ui.LogOutController.java");

    /**
     * Whenever the user hits the back button it will send him back to the log
     * in window.
     */
    @FXML
    private void handleButtonClose(ActionEvent event) throws IOException {
        LOG.log(Level.INFO, "Going back to sign in");
        //It gets the FXML of the sign-in window
        stage.close();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initializes and launches the window.
     *
     * @param root the parent of the window
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        LOG.log(Level.INFO, "Log out window showing");
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Log out");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method changes the text in the window to show the user's name.
     *
     * @param user the user that logged in
     */
    public void setUsername(User user) {
        txtWindow.setText("Hello, " + user.getFullName());
    }

}
