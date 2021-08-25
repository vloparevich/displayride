package tests.android.settingsview;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.SettingsViewDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;

import static java.lang.System.getProperty;

import static org.testng.Assert.assertEquals;

public class UiElementsTextConsistencyTest extends BaseTest {

    private SettingsViewDR sv;
    private MainViewDR mv;
    private MoreOptionMenuDR mom;

    String testName = null;

    @BeforeClass
    public void setUpLaunching() {

        testName = this.getClass().getSimpleName();
        sv = new SettingsViewDR(driver);
        mv = new MainViewDR(driver);
        mom = new MoreOptionMenuDR(driver);

        String accessotyId = getProperty("accessoryId");
    }


    @BeforeMethod
    public void onStartingMethodWithinClass() {
        driver.launchApp();

        //Navigation to Settings Screen
        mv.navigateToSettingsViewIfNeed();
    }


    @AfterMethod
    public void onFinishingMethodWithinCLass() {

        //Navigate to the Main View
        sv.continueTap();

        driver.closeApp();
    }


    @Test(invocationCount = 10) //Verify text consistency on the Settings Page
    public void verifyUiElementsTextConsistency() throws InterruptedException, IOException {


        //Check text consistency on Carrier Buttons
        assertEquals(sv.getTextUiElement(sv.lyftButtonText), "Lyft");
        assertEquals(sv.getTextUiElement(sv.uberButtonText), "Uber");


        //Check text consistency on the Continue Button
        assertEquals(sv.getTextUiElement(sv.continueButton), "Continue");

        //Chech the Text on the Floating Widget
        assertEquals(sv.getTextFromFW(), "Floating Widget");


        //TODO Sellect all Cab services you work for text Check
    }


    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);
    }

}
