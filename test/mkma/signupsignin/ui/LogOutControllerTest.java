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
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 * TestFX test class for LogOutController.
 * @author Aitor Garcia
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogOutControllerTest extends ApplicationTest{
    
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
        clickOn("#txtUser");
        write("kerman");
        clickOn("#txtPass");
        write("1Aqwe");
        clickOn("#btnSignIn");
        verifyThat("#windowSignOut", isVisible());
    }
    
    @Test
    public void testB_goBack(){
        clickOn("#btnClose");
        verifyThat("#windowSignIn", isVisible());
    }
    
}
