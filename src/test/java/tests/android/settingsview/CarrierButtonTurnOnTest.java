package tests.android.settingsview;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.PopUpMenuCarriersMainViewDR;
import objects.android.SettingsViewDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertEquals;

public class CarrierButtonTurnOnTest extends BaseTest {

    private SettingsViewDR sv;
    private MainViewDR mv;
    private MoreOptionMenuDR mom;
    private PopUpMenuCarriersMainViewDR popCM;

    String testName = null;

    @BeforeClass
    public void setUpLaunching() {

        testName = this.getClass().getSimpleName();
        sv = new SettingsViewDR(driver);
        mv = new MainViewDR(driver);
        mom = new MoreOptionMenuDR(driver);
        popCM = new PopUpMenuCarriersMainViewDR(driver);

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


    @Test(invocationCount = 8) //Verify One carrier button can TurnOn and new Settings will Apply on the Main View
    public void verifyThatCarrierButtonTurnOn() {

        //Turn Off All Buttons
        sv.allCarriersButtonsTurnOff();

        //Turn On Uber button
        sv.carrierButtonTurnOn(sv.uberButton);

        //Check attribute checked status
        assertEquals(sv.getCheckedStatusUiElement(sv.uberButton), "true");

        //Navigate to the Main View
        sv.continueTap();

        //Verify That Uber Appears in the Main Veiw
        mv.selectCarrierExpandableField();
        assertEquals(popCM.getListOfCarriersFromPopUpMenu().get(0).getAttribute("text"), "Uber");
    }


    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);
    }

}
