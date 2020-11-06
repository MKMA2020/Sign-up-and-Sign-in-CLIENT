/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.ui;

import javafx.stage.Stage;
import mkma.signupsignin.application.App;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
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
public class PresentationTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
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
        sleep(4000);
        clickOn("#btnSignUp");
        sleep(4000);
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
        sleep(4000);
        clickOn("#btnBack");
        clickOn("#txtUser");
        write("Alberto");
        clickOn("#txtPass");
        write("Alberto!Garcia8");
        clickOn("#btnSignIn");
        verifyThat("#windowSignOut", isVisible());
        sleep(4000);
        clickOn("#btnClose");
        verifyThat("#windowSignIn", isVisible());
        sleep(4000);
    }


}
