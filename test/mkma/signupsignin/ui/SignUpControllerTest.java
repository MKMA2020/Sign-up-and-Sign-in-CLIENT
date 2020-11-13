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
        clickOn("#SignInbtnSignUp");
        
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
        
        verifyThat("#SignUpTxtUser", hasText(""));
        verifyThat("#SignUpTxtPass", hasText(""));
        verifyThat("#SignUpTxtPassAgain", hasText(""));
        verifyThat("#SignUpTxtEmail", hasText(""));
        verifyThat("#SignUpTxtName", hasText(""));
        verifyThat("#SignUpBtnSignUp", isDisabled());
        verifyThat("#SignUpBtnBack", isEnabled());
        
    }

    /**
     * This method will verify that SignUpButton is only enabled when all fields
     * contain text. If there is at least one empty field SignUpButton should be
     * disabled.
     */
    @Test
    public void testB_SignUpButtonEnabled() {
                
        clickOn("#SignUpTxtUser");
        write("asd");
        verifyThat("#SignUpBtnSignUp", isDisabled());

        clickOn("#SignUpTxtPass");
        write("asd");
        verifyThat("#SignUpBtnSignUp", isDisabled());

        clickOn("#SignUpTxtPassAgain");
        write("asd");
        verifyThat("#SignUpBtnSignUp", isDisabled());

        clickOn("#SignUpTxtEmail");
        write("asd");
        verifyThat("#SignUpBtnSignUp", isDisabled());

        clickOn("#SignUpTxtName");
        write("asd");
        verifyThat("#SignUpBtnSignUp", isEnabled());
        

    }

    /**
     * This method will verify that an alert is thrown if the user is too short.
     */
    @Test
    public void testC_UsernameShort() {
        clickOn("#SignUpTxtUser");
        write("a");
        clickOn("#SignUpTxtPass");
        write("Alberto!Garcia8");
        clickOn("#SignUpTxtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#SignUpTxtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#SignUpTxtName");
        write("Alberto García");
        clickOn("#SignUpBtnSignUp");
        verifyThat("The username is too short.\n", isVisible());
        clickOn("Aceptar");
    }
    
    /**
     * This method will verify that an alert is thrown if the user is too long.
     */
    @Test
    public void testD_UsernameLong() {
       
        clickOn("#SignUpTxtUser");
        write("AlbertoAlbertoAlbertoAlberto");
        clickOn("#SignUpTxtPass");
        write("Alberto!Garcia8");
        clickOn("#SignUpTxtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#SignUpTxtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#SignUpTxtName");
        write("Alberto García");
        clickOn("#SignUpBtnSignUp");
        verifyThat("The username is too long.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the password is too
     * short.
     */
    @Test
    public void testE_PasswordShort() {
        clickOn("#SignUpTxtUser");
        write("Alberto");
        clickOn("#SignUpTxtPass");
        write("1aA");
        clickOn("#SignUpTxtPassAgain");
        write("1aA");
        clickOn("#SignUpTxtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#SignUpTxtName");
        write("Alberto García");
        clickOn("#SignUpBtnSignUp");
        verifyThat("The password is too short.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the password does not
     * contain one upper case, one lowercase and a number.
     */
    @Test
    public void testF_PasswordWeak() {
        clickOn("#SignUpTxtUser");
        write("Alberto");
        clickOn("#SignUpTxtPass");
        write("asdasd");
        clickOn("#SignUpTxtPassAgain");
        write("asdasd");
        clickOn("#SignUpTxtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#SignUpTxtName");
        write("Alberto García");
        clickOn("#SignUpBtnSignUp");
        verifyThat("The password has to contain\nat least an upper-case,\nlower-case and a number.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the password does not
     * match.
     */
    @Test
    public void testG_PasswordNoMatch() {
        clickOn("#SignUpTxtUser");
        write("Alberto");
        clickOn("#SignUpTxtPass");
        write("Alberto!Garcia8");
        clickOn("#SignUpTxtPassAgain");
        write("Alberto!Garcia81");
        clickOn("#SignUpTxtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#SignUpTxtName");
        write("Alberto García");
        clickOn("#SignUpBtnSignUp");
        verifyThat("The passwords don´t match.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the Email format is
     * incorrect.
     */
    @Test
    public void testH_EmailWrong() {
        clickOn("#SignUpTxtUser");
        write("Alberto");
        clickOn("#SignUpTxtPass");
        write("Alberto!Garcia8");
        clickOn("#SignUpTxtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#SignUpTxtEmail");
        write("albertogarciagmailcom");
        clickOn("#SignUpTxtName");
        write("Alberto García");
        clickOn("#SignUpBtnSignUp");
        verifyThat("The email format is not valid.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that once pushed the button Sign Up works.
     */
    @Test
    public void testI_SignUpButtonChanged() {
        clickOn("#SignUpTxtUser");
        write("Alberto");
        clickOn("#SignUpTxtPass");
        write("Alberto!Garcia8");
        clickOn("#SignUpTxtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#SignUpTxtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#SignUpTxtName");
        write("Alberto García");
        clickOn("#SignUpBtnSignUp");
        verifyThat("User succesfully created", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that btnBack opens Sign In window and closes Sign
     * Up window.
     */
    @Test
    public void testJ_btnBackOK() {
        clickOn("#SignUpBtnBack");
        verifyThat("#windowSignIn", isVisible());
    }

}
