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
 * Class for the logout controller and its methods. If the user gets here a
 * greeting will be shown with his name on it.
 *
 * @author Martin Gros
 */
public class LogOutController implements Initializable {

    @FXML
    private Stage stageLogOut;
    @FXML
    private Button btnClose;
    @FXML
    private Text txtWindow;

    @FXML
    /**
     * Whenever the user hits the back button it will send him back to the log
     * in window.
     */

    private void handleButtonClose(ActionEvent event) throws IOException {
        Stage stageSignIn = new Stage();
        //It gets the FXML of the sign-in window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Parent root = (Parent) loader.load();
        //It creates a controller for the window and runs it
        SignInController controller = (loader.getController());
        controller.setStageSignIn(stageSignIn);
        controller.initStage(root);
        stageLogOut.close();
    }

    public Stage getStageLogOut() {
        return stageLogOut;
    }

    public void setStageLogOut(Stage stageLogOut) {
        this.stageLogOut = stageLogOut;
    }

    /**
     * Initializes and launches the window.
     *
     * @param root the parent of the window
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stageLogOut.setScene(scene);
        stageLogOut.setTitle("Log Out");
        stageLogOut.setResizable(false);
        stageLogOut.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
