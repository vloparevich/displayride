package tests.android.settingsview;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.SettingsViewDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertEquals;

public class CarriersButtonsTurnOffTest extends BaseTest {

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

        //After Test Navigate to the Main View
        sv.carrierButtonTurnOn(sv.uberButton);
        sv.continueTap();

        driver.closeApp();
    }



    @Test(invocationCount = 10) //Verify that All carrier buttons can TurnOff and User can't Navigate to the Main View
    public void verifyThatCarrierButtonTurnOff() {

        //Tap on all Carriers Buttons
        sv.allCarriersButtonsTurnOff();

        //Get Attribute and check It
        for (int i = 0; i < sv.getListOfAllCarriersButtons().size()-1; i++) {
            assertEquals(sv.getListOfAllCarriersButtons().get(i).getAttribute("checked"), "false");
        }

        //Tap On the Continue Button
        sv.continueTap();

        //Notification Message Should Appear
        assertEquals(sv.getTextUiElement(sv.notification), "Please Select the Cab Service");
    }


    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);
    }

}
