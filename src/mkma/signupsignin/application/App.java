package mkma.signupsignin.application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import mkma.signupsignin.ui.*;

/**
 * Application from the client side, loads the stage and calls the controller
 * which initializes it.
 *
 * @author Kerman Rodríguez
 */
public class App extends Application {

    /**
     * Loads the fxml file and calls the controller in order to initialize the
     * stage.
     *
     * @param primaryStage the stage to set
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mkma/signupsignin/ui/SignIn.fxml"));
        Parent root = (Parent) loader.load();

        SignInController controller = (loader.getController());
        controller.setStage(primaryStage);
        controller.initStage(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
