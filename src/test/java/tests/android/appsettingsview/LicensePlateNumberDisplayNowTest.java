package tests.android.appsettingsview;

import baseplatformANDROID.BaseTest;
import io.appium.java_client.android.AndroidElement;
import objects.android.AppSettingsViewDR;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.SettingsViewDR;
import objects.android.PopUpMenuCarriersMainViewDR;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ReusMeth;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class LicensePlateNumberDisplayNowTest extends BaseTest {

    private MainViewDR mv;
    private MoreOptionMenuDR mom;
    private AppSettingsViewDR ast;
    private SettingsViewDR st;
    private PopUpMenuCarriersMainViewDR popm;
    private String[] aCarrier = {"InstaRyde", "DiDi", "Uber"};
    private String[] addedLicensePlateNumber = {"Taxi\uD83D\uDE96", "タクシー\uD83D\uDE96"};

    private int testCount = 1;

    @BeforeClass
    public void setUpLaunching() {

        mv = new MainViewDR(driver);
        mom = new MoreOptionMenuDR(driver);
        ast = new AppSettingsViewDR(driver);
        st = new SettingsViewDR(driver);
        popm = new PopUpMenuCarriersMainViewDR(driver);
    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() +
                " --- Test Count = " + testCount + "\033[0m");
        testCount++;
        //driver.launchApp();
    }

    @AfterMethod
    public void onFinishingMethodWithinCLass() {
        //driver.closeApp();
    }

    //Verify that the inputted Plate Number can be added to Cab Service list on Main View window
    @Test
    public void addedCabServiceCanBeSelectedTest() throws InterruptedException {

        mv.moreOptionButtonTap();
        mom.settingsButtonTap();

        mv.moreOptionButtonTap();
        mom.resetAppButtonTap();

        mom.resetAppButtonYesTap();

        ReusMeth.waiting(5);

        for (int i = 0; i < aCarrier.length; i++) {
            st.tapOnParticularCarrier(aCarrier[i]);
        }

        ReusMeth.waiting(3);
        st.continueTap();

        ReusMeth.waiting(3);
        mv.moreOptionButtonTap();

        mom.appSettingsButtonTap();

        ast.inputPlatenumberIntoField(addedLicensePlateNumber[1]);
        ReusMeth.waiting(5);
        ast.tapContinueButton();

        ReusMeth.waiting(5);
        mv.selectCarrierExpandableField();

        ReusMeth.waiting(5);
        System.out.println("\033[0;95m" +   addedLicensePlateNumber[1]   + " --- Test Count = " + testCount + "\033[0m");
        popm.tapOnLicensePlateNumber(addedLicensePlateNumber[1]);

        assertTrue(mv.getCabServiceName().equals(addedLicensePlateNumber[1]), "Failed. Car Service Setting up is wrong.");

    }
}

