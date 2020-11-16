package mkma.signupsignin.ui;

import javafx.stage.Stage;
import mkma.signupsignin.application.App;
import org.junit.After;
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
    
    @After
    public void afterTest () {
        clickOn("#btnBack");
    }


    /**
     * This method will verify the stage on launch. TextBoxes will be empty by
     * default. SignUpButton will be disabled as default. Back button will be
     * enabled by default.
     */
    @Test
    public void testA_initialstate() {
        verifyThat("#txtUserRegister", hasText(""));
        verifyThat("#txtPassRegister", hasText(""));
        verifyThat("#txtPassAgain", hasText(""));
        verifyThat("#txtEmail", hasText(""));
        verifyThat("#txtName", hasText(""));
        verifyThat("#btnSignUpRegister", isDisabled());
        verifyThat("#btnBack", isEnabled());
        
    }

    /**
     * This method will verify that SignUpButton is only enabled when all fields
     * contain text. If there is at least one empty field SignUpButton should be
     * disabled.
     */  
    @Test
    public void testB_SignUpButtonEnabled() {

        clickOn("#txtUserRegister");
        write("asd");
        verifyThat("#btnSignUpRegister", isDisabled());

        clickOn("#txtPassRegister");
        write("asd");
        verifyThat("#btnSignUpRegister", isDisabled());

        clickOn("#txtPassAgain");
        write("asd");
        verifyThat("#btnSignUpRegister", isDisabled());

        clickOn("#txtEmail");
        write("asd");
        verifyThat("#btnSignUpRegister", isDisabled());

        clickOn("#txtName");
        write("asd");
        verifyThat("#btnSignUpRegister", isEnabled());

    }

    /**
     * This method will verify that an alert is thrown if the user is too short.
     */
    @Test
    public void testC_UsernameShort() {
        clickOn("#txtUserRegister");
        write("a");
        clickOn("#txtPassRegister");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUpRegister");
        verifyThat("The username is too short.\n", isVisible());
        clickOn("Aceptar");
    }
    /**
     * This method checks if the username is too long
     */
    @Test
    public void testD_UsernameLong() {
        clickOn("#txtUserRegister");
        write("aaaaaaaaaaaaaaaaaaaaa");
        clickOn("#txtPassRegister");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUpRegister");
        verifyThat("The username is too long.\n", isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will verify that an alert is thrown if the password is
     * too short    
     */
    @Test
    public void testE_PasswordShort() {
        clickOn("#txtUserRegister");
        write("Alberto");
        clickOn("#txtPassRegister");
        write("1Aq");
        clickOn("#txtPassAgain");
        write("1Aq");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUpRegister");
        verifyThat("The password is too short.\n", isVisible());
        clickOn("Aceptar");
    }
    /**
     * This method checks if the password's format is valid.
     */
    @Test
    public void testF_PasswordFormat() {
        clickOn("#txtUserRegister");
        write("Alberto");
        clickOn("#txtPassRegister");
        write("aaaaa");
        clickOn("#txtPassAgain");
        write("aaaaa");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUpRegister");
        verifyThat("The password needs to contain at least an upper-case, lower-case and a number.\n", isVisible());
        clickOn("Aceptar");
    }
    
    /**
     * This method will verify that an alert is thrown if the password does not match.
     */
    @Test
    public void testG_PasswordNoMatch(){
        clickOn("#txtUserRegister");
        write("Alberto");
        clickOn("#txtPassRegister");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia81");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUpRegister");
        verifyThat("The passwords don´t match.\n",isVisible()); 
        clickOn("Aceptar");
    }
    
    /**
     * This method will verify that an alert is thrown if the Email format is incorrect.
     */
    @Test
    public void testH_EmailWrong(){
        clickOn("#txtUserRegister");
        write("Alberto");
        clickOn("#txtPassRegister");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarciagmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUpRegister");
        verifyThat("The email format is not valid.\n",isVisible()); 
        clickOn("Aceptar");
    }

    /**
     * This method will verify that once pushed the button Sign Up changes to
     * Signed Up.
     */
    @Test
    public void testI_SignUpButtonChanged() {
        clickOn("#txtUserRegister");
        write("Alberto");
        clickOn("#txtPassRegister");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUpRegister");
        verifyThat("#btnSignUpRegister", isDisabled());
    }

    /**
     * This methods check that an alert shows when the user tries to register
     * an existing user
     */
    @Test
    public void testJ_existingUser() {
        clickOn("#txtUserRegister");
        write("Alberto");
        clickOn("#txtPassRegister");
        write("Alberto!Garcia8");
        clickOn("#txtPassAgain");
        write("Alberto!Garcia8");
        clickOn("#txtEmail");
        write("albertogarcia@gmail.com");
        clickOn("#txtName");
        write("Alberto García");
        clickOn("#btnSignUpRegister");
        verifyThat("The user you are trying to create already exists.",isVisible()); 
        clickOn("Aceptar");
    }

}
