/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import java.util.concurrent.TimeoutException;
import javafx.stage.Stage;
import mkma.signupsignin.application.App;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.anything;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;


/**
 * TestFX test class for SignInController.
 * @author Aitor Garcia
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInControllerTest extends ApplicationTest{
    
    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
    }
    
    @Override
    public void stop(){}
    
    /*@BeforeClass
    public static void setUpClass() throws TimeoutException {
        
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(App.class);
   }
    */
    /**
     * Method that checks that the initial characteristics of the window are correct.
     */
    @Test
    public void TestA_initislstate() {
        verifyThat("#txtUser", hasText(""));
        verifyThat("#txtPass", hasText(""));
        verifyThat("#btnSignIn", isDisabled());
    }
    
    /**
     * Method that checks if the login button activates without the necessary fields being filled.
     */
   @Test
    public void TestB_signInDisabled(){
        clickOn("#txtUser");
        write("ratherlongusername");
        verifyThat("#btnSignIn", isDisabled());
        eraseText(18);
        clickOn("#txtPass");
        write("alsoratherlongpassword");
        verifyThat("#btnSignIn", isDisabled());
        eraseText(22);
        verifyThat("#btnSignIn", isDisabled());
    }
    
    /**
     * Method that checks if the login button activates when the necessary fields are filled.
     */
    @Test
    public void TestC_signInEnabled(){
        clickOn("#txtUser");
        write("validusername");
        clickOn("#txtPass");
        write("alsovalidpassword");
        verifyThat("#btnSignIn", isEnabled());
    }
    
    /**
     * Method that checks if an alert pops up when a username that is too long is introduced.
     */
   @Test
   public void TestD_userTooLong(){
        clickOn("#txtUser");
        write("invaliduseeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeer");
        clickOn("#txtPass");
        write("validpassword");
        clickOn("#btnSignIn");
        verifyThat("#alertlongUser",anything());        
        clickOn("#btnOkL");
   }
   
   /**
    * Method that checks if an alert pops up when a username that is too short is introduced.
    */
   @Test
   public void TestE_userTooShort(){
        clickOn("#txtUser");
        write("user");
        clickOn("#txtPass");
        write("validpassword");
        clickOn("#btnSignIn");
        verifyThat("#alertShortUser",anything());
        clickOn("#btnOkS");
   }
   
    
   /**
    * Method that checks if an alert pops up when a username that is too long is introduced, in case the program couldn't take it.
    */
   /*
   @Test
   public void TestF_userComicallyLong() {
       clickOn("#txtUser");
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#txtPass");
        write("validpassword");
        clickOn("#btnSignIn");
        verifyThat("Username is too long",isVisible());        
        clickOn("#btnOkL");
   }
   */
   
   /**
    * Method that checks if the program crashes when a password that is too long is introduced, in case the program couldn't take it.
    */
   /*
   @Test
   @Ignore
   public void TestG_passComicallyLong() {
       clickOn("#txtUser");
        write("validusername");
        clickOn("#txtPass");
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignIn");
        verifyThat("User or password are incorrect.",isVisible());
        clickOn("#btnOkH");
        
   }
   */
   /**
    * Method that introduces the wrong login but the right password.
    */
   @Test
    public void TestH_SigningIn(){
        clickOn("#txtUser");
        write("martinWrong");
        clickOn("#txtPass");
        write("Aa123");
        clickOn("#btnSignIn");
        verifyThat("User or password are incorrect.",isVisible());
        clickOn("#btnOkH");
        
    }
    /**
    * Method that introduces the right login but the wrong password.
    */
   @Test
    public void TestI_SigningIn(){
        clickOn("#txtUser");
        write("martinG");
        clickOn("#txtPass");
        write("Aa123");
        clickOn("#btnSignIn");
        verifyThat("User or password are incorrect.",isVisible());        
        clickOn("#btnOkH");
    }
   
   /**
    * Method that introduces correct login information and checks if the next window loads.
    */
   @Test
    public void TestJ_SigningIn(){
        clickOn("#txtUser");
        write("martinG");
        clickOn("#txtPass");
        write("Aa1234");
        clickOn("#btnSignIn");
        verifyThat("#windowSignOut", isVisible());
    }
    /**
    * Method for when the server is off.
    */
   @Test
    public void TestK_SigningIn(){
        clickOn("#txtUser");
        write("martinG");
        clickOn("#txtPass");
        write("Aa1234");
        clickOn("#btnSignIn");
        verifyThat("An unexpected error ocurred on the server.",isVisible());        
        clickOn("#btnOkH");
    }
}
