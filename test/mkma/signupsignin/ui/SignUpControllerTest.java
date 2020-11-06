package mkma.signupsignin.ui;

import javafx.stage.Stage;
import mkma.signupsignin.application.App;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.anything;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import org.testfx.util.WaitForAsyncUtils;

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
        clickOn("#btnSignUp");
    }

    @Override
    public void stop() {
    }

    /**
     * This method will verify the stage on lauch. TextBoxes will be empty by
     * default. SignUpButton will be disabled as default. Back button will be
     * enabled by default.
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
     * This method will verify that SignUpButton is only enabled when all fields
     * contain text. If there is at least one empty field SignUpButton should be
     * disabled.
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
        verifyThat("#btnSignUp", isEnabled());

    }

    /**
     * This method will verify that an alert is thrown if the user is too short.
     */
    @Test
    public void testC_UsernameShort() {
        clickOn("#txtUser");
        write("a");
        clickOn("#txtPass");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUp");
        verifyThat("#listAllAlerts", anything());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the password does not
     * meet criteria.
     */
    @Test
    public void testD_PasswordShort() {
        clickOn("#txtUser");
        write("Alberto");
        clickOn("#txtPass");
        write("1");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUp");
        verifyThat("#listAllAlerts", anything());
        clickOn("Aceptar");
    }
    
                   /**
     * This method will verify that an alert is thrown if the password does not match.
     */
    @Test
    public void testE_PasswordNoMatch(){
        clickOn("#txtUser");
        write("Alberto");
        clickOn("#txtPass");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia81");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUp");
        verifyThat("#listAllAlerts",anything()); 
        clickOn("Aceptar");
    }
    
                       /**
     * This method will verify that an alert is thrown if the Email format is incorrect.
     */
    @Test
    public void testF_EmailWrong(){
        clickOn("#txtUser");
        write("Alberto");
        clickOn("#txtPass");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia81");
        clickOn("#txtEmail");
        write("albertogarciagmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUp");
        verifyThat("#listAllAlerts",anything()); 
        clickOn("Aceptar");
    }

    /**
     * This method will verify that once pushed the button Sign Up changes to
     * Signed Up.
     */
    @Test
    public void testG_SignUpButtonChanged() {
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
        verifyThat("#Signed Up", isDisabled());
    }

    /**
     * This method will verify that btnBack opens Sign In window and closes Sign
     * Up window.
     */
    @Test
    public void testH_btnBackOK() {
        clickOn("#btnBack");
        verifyThat("#windowSignIn", isVisible());
    }

}
