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
        verifyThat("The username is too short.\n", isVisible());
        clickOn("Aceptar");
    }
    
        /**
     * This method will verify that an alert is thrown if the user is too long.
     */
    @Test
    public void testD_UsernameLong() {
        clickOn("#txtUser");
        write("AlbertoAlbertoAlbertoAlberto");
        clickOn("#txtPass");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUp");
        verifyThat("The username is too long.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the password is too
     * short.
     */
    @Test
    public void testE_PasswordShort() {
        clickOn("#txtUser");
        write("Alberto");
        clickOn("#txtPass");
        write("1aA");
        clickOn("#txtPassAgain");
        write("1aA");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUp");
        verifyThat("The password is too short.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the password does not
     * contain one upper case, one lowercase and a number.
     */
    @Test
    public void testF_PasswordWeak() {
        clickOn("#txtUser");
        write("Alberto");
        clickOn("#txtPass");
        write("asdasd");
        clickOn("#txtPassAgain");
        write("asdasd");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUp");
        verifyThat("The password has to contain\nat least an upper-case,\nlower-case and a number.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the password does not
     * match.
     */
    @Test
    public void testG_PasswordNoMatch() {
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
        verifyThat("The passwords don´t match.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the Email format is
     * incorrect.
     */
    @Test
    public void testH_EmailWrong() {
        clickOn("#txtUser");
        write("Alberto");
        clickOn("#txtPass");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarciagmailcom");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUp");
        verifyThat("The email format is not valid.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that once pushed the button Sign Up works.
     */
    @Test
    public void testI_SignUpButtonChanged() {
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
        verifyThat("#btnSignUp", isEnabled());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that btnBack opens Sign In window and closes Sign
     * Up window.
     */
    @Test
    public void testJ_btnBackOK() {
        clickOn("#btnBack");
        verifyThat("#windowSignIn", isVisible());
    }

}
