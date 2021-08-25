package tests.android;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertTrue;

public class SecondMainViewDRTest extends BaseTest {

    private MainViewDR mv;

    String testName = null;

    @BeforeClass
    public void setUpLaunching() {

        testName = this.getClass().getSimpleName();
        mv = new MainViewDR(driver);


        String accessoryId = getProperty("accessoryId");
        System.out.println("\033[0;95m riderNameValue: " + testName +"\033[0m");
    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
        driver.launchApp();
    }

    @AfterMethod
    public void onFinishingMethodWithinCLass() {
        driver.closeApp();
    }

    @Parameters({"riderName"})
    @Test
    public void nameIsDisplayedInTextPreviewFrame(String riderNameValue) throws IOException, InterruptedException {

        //mv.displayNowTap();
        mv.inputNameIntoField(riderNameValue);
        ReusMeth.waiting(20);
        mv.displayNowTap();
        ReusMeth.waiting(10);
        mv.gettingNameFromNameField();
        ReusMeth.waiting(10);
        mv.getContentFromTextPreviewFrameArray();
        ReusMeth.waiting(10);
        assertTrue(mv.verifyNameIsPresentedInMessageFrame(), "The name is displayed Incorrectly");
    }

    @AfterClass
    public void tearDown() {

    }


}
