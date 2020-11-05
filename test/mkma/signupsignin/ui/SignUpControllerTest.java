package mkma.signupsignin.ui;

import javafx.stage.Stage;
import mkma.signupsignin.application.App;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 * TestFX test class for SignUpController.
 *
 * @author Martin Valietne Ainz
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
    }

    @Override
    public void stop() {
    }
    
    /**
     * This method will verify the stage on lauch.
     * TextBoxes will be empty by default.
     * SignUpButton will be disabled as default.
     * Back button will be enabled by default.
     */
    @Test
    public void testA_initialstate() {
        verifyThat("#txtUser", hasText(""));
        verifyThat("#txtPass", hasText(""));
        verifyThat("#txtPassAgain", hasText(""));
        verifyThat("#txtEmail", hasText(""));
        verifyThat("#txtName", hasText(""));
        verifyThat("#btnSignUp", isDisabled());
        verifyThat("#btnBack", isEnabled());
    }
    
    /**
     * This method will verify that SignUpButton is only enabled when all fields contain text.
     * If there is at least one empty field SignUpButton should be disabled.
     */
    @Test
    public void testB_SignUpButtonEnabled() {
        
        clickOn("#txtUser");
        write("asd");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#txtPass");
        write("asd");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#txtPassAgain");
        write("asd");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#txtEmail");
        write("asd");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#txtName");
        write("asd");
        verifyThat("btnSignUp", isEnabled());

    }
    
    /**
     * This method will verify that once pushed the button Sign Up changes to Signed Up.
     */
    public void testC_SignUpButtonChanged(){
        clickOn("#txtUser");
        write("Alberto");
        clickOn("#txtPass");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUp");
        verifyThat("#btnSignUp", hasText("Signed Up"));
    }
    /**
     * This method will verify that btnBack opens Sign In window and closes Sign Up window.
     */
    public void testD_btnBackOK(){
        clickOn("#btnBack");
        verifyThat("#windowSignIn", isVisible());
        verifyThat("#windowSignUp", isDisabled());
    }

}
