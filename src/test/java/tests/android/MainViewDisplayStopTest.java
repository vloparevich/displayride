package tests.android;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.popupmenus.PopUpMenuOfStopDisplayButtonDR;
import objects.android.popupmenus.ScheduleDisplayDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertTrue;

public class MainViewDisplayStopTest extends BaseTest {

    private MainViewDR mv;
    private ScheduleDisplayDR sd;
    private PopUpMenuOfStopDisplayButtonDR pm;
    private long fiveMins = 300;

    String testName = null;
    private int testCount = 1;

    @BeforeClass
    public void setUpLaunching() {

        testName = this.getClass().getSimpleName();
        mv = new MainViewDR(driver);
        pm = new PopUpMenuOfStopDisplayButtonDR(driver);

        String accessoryId = getProperty("accessoryId");

        driver.launchApp();
        mv.updateApplicationSignButtonOkTap();
    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() + " --- Test Count = " + testCount + "\033[0m");
        testCount ++;

        //driver.launchApp();
    }

    @AfterMethod
    public void onFinishingMethodWithinCLass()  throws InterruptedException {
        ReusMeth.waiting(20);
    }

    // Verify that "Stop Display" button "Yes" turns display OFF
    @Parameters({"riderName"})
    @Test(invocationCount = 5)
    public void previewIsOFFWithStopTapYes(String riderNameValue) throws IOException, InterruptedException {
        mv.inputNameIntoField(riderNameValue);
        mv.displayNowTap();

        ReusMeth.waiting(10);
        mv.stopDisplayTap();
        ReusMeth.waiting(10);
        pm.stopDisplayYesTap();

        ReusMeth.waiting(20);

        assertTrue(mv.getContentFromTextPreviewFrameString().equals("OFF"));
    }

    // Verify that "Stop Display" button "No" DON'T turn display OFF
    @Parameters({"riderName"})
    @Test(invocationCount = 5)
    public void previewIsNotOFFWithStopTapNo(String riderNameValue) throws IOException, InterruptedException {
        mv.inputNameIntoField(riderNameValue);
        mv.displayNowTap();

        mv.gettingNameFromNameField();
        mv.getContentFromTextPreviewFrameArray();

        ReusMeth.waiting(10);
        mv.stopDisplayTap();
        ReusMeth.waiting(3);
        pm.stopDisplayNoTap();

        assertTrue(mv.verifyNameIsPresentedInMessageFrame(), "The name is displayed Incorrectly");

        mv.stopDisplayTap();
        pm.stopDisplayYesTap();

    }

    //Verify that "Stop Display" mode stops the display after "Display Later" command issued with 5 min delay and display continue for 5 min
    @Parameters({"riderName"})
    @Test  //(invocationCount = 5)
    public void stopDisplayAfterDisplayLaterIssued(String riderNameValue)  throws InterruptedException{

        mv.inputNameIntoField(riderNameValue);

        mv.displayLaterTap();
        mv.scheduleButtonTap();
        ReusMeth.waiting(5);

        mv.stopDisplayTap();
        ReusMeth.waiting(2);
        pm.stopDisplayYesTap();

        assertTrue(mv.getTextOfEditNameString().equals("Add Name"), "Display Later is not successfully stopped");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        ReusMeth.waiting(20);
    }


}
