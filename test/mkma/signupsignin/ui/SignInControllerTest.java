/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import java.util.concurrent.TimeoutException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mkma.signupsignin.application.App;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.anything;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.matcher.base.WindowMatchers;
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
    
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(App.class);
   }
    
    @Test
    public void testA_initislstate() {
        verifyThat("#txtUser", hasText(""));
        verifyThat("#txtPass", hasText(""));
        verifyThat("#btnSignIn", isDisabled());
    }
    
   /* @Test
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
    
    @Test
    public void TestC_signInEnabled(){
        clickOn("#txtUser");
        write("validusername");
        clickOn("#txtPass");
        write("alsovalidpassword");
        verifyThat("#btnSignIn", isEnabled());
    }
    
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
    
   @Test
   public void TestF_userComicallyLong() {
       clickOn("#txtUser");
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#txtPass");
        write("validpassword");
        clickOn("#btnSignIn");
        verifyThat("#alertlongUser",anything());        
        clickOn("#btnOkL");
   }
   
   @Test
   public void TestG_passComicallyLong() {
       clickOn("#txtUser");
        write("validusername");
        clickOn("#txtPass");
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignIn");
        
   }*/
   
   @Test
    public void TestH_SigningIn(){
        clickOn("#txtUser");
        write("username");
        clickOn("#txtPass");
        write("alsovalidpassword");
        clickOn("#btnSignIn");
        verifyThat("#windowSignOut", isVisible());
    }
}
