/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import javafx.stage.Stage;
import mkma.signupsignin.application.App;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

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
    
    /**
     * Method that leads to the logout window and then checks whether it exists or not.
     */
    @Test
    public void testA_initislstate_return() {
        clickOn("#txtUser");
        write("kerman");
        clickOn("#txtPass");
        write("1Aqwe");
        clickOn("#btnSignIn");
        verifyThat("#windowSignOut", isVisible());
        clickOn("#btnClose");
        verifyThat("#windowSignIn", isVisible());
    }  
}
