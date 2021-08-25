package tests.android.settingsview;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.SettingsViewDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertEquals;

public class AllCarriersButtonsTurnOn extends BaseTest {

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



    @Test(invocationCount = 5) //Verify all buttons can TurnOn
    public void verifyThatAllButtonsTurnOn() {

        //Tap on all Carriers Buttons
        sv.allCarriersButtonsTurnOn();

        //Get Attribute and check It
        for (int i = 0; i < sv.getListOfAllCarriersButtons().size()-1; i++) {
            assertEquals(sv.getListOfAllCarriersButtons().get(i).getAttribute("checked"), "true");
        }

        //Navigate to the Main View
        sv.continueTap();

        //TODO SL Assert All Elements at the Main View
    }


    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);
    }

}
