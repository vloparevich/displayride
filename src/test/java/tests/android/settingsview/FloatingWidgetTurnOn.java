package tests.android.settingsview;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.SettingsViewDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertEquals;

public class FloatingWidgetTurnOn extends BaseTest {

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
        driver.closeApp();
    }


    @Test(invocationCount = 12) //Verify FW can TurnON and Appear in the Uber App
    public void verifyThatFloatingWidgetTurnOn() {

        //Turn on Floating Widget
        sv.floatingWidgetTurnOn();

        //Check the Floating Widget Status
        assertEquals(sv.getCheckedStatusUiElement(sv.floatingWidgetSwitch), "true");

        //Turn On Uber Button
        sv.carrierButtonTurnOn(sv.uberButton);

        //Check attribute checked status
        assertEquals(sv.getCheckedStatusUiElement(sv.uberButton), "true");

        //Navigate no the Main View
        sv.continueTap();

        //TODO SL Check Connection

        //TODO SL Open Uber App
    }


    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);
    }

}
