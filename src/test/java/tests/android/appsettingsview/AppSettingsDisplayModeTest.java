package tests.android.appsettingsview;


import baseplatformANDROID.BaseTest;
import objects.android.AppSettingsViewDR;
import objects.android.MainViewDR;
import objects.android.AppSettingsViewDR;
import objects.android.MoreOptionMenuDR;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ReusMeth;

import static org.testng.Assert.assertTrue;

public class AppSettingsDisplayModeTest extends BaseTest {

    private MainViewDR mv;
    private AppSettingsViewDR ast;
    private MoreOptionMenuDR mom;

    private int testCount = 1;

    @BeforeClass
    public void setUpLaunching() {

        mv = new MainViewDR(driver);
        ast = new AppSettingsViewDR(driver);
        mom = new MoreOptionMenuDR(driver);
    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() +
                " --- Test Count = " + testCount + "\033[0m");
        testCount++;
        driver.launchApp();
    }

    @AfterMethod
    public void onFinishingMethodWithinCLass() {

        //driver.closeApp();
    }

    //Verify that "Day Mode" can be selected, the accessary shows "Day Mode"
    @Test
    public void selectDayModeTest() throws InterruptedException{

        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();

        ast.aRidioButtonTurnOff("Night Mode");
        ReusMeth.waiting(3);
        ast.aRadioButtonTurnOn("Day Mode");
        assertTrue(ast.getCheckedStatusUIElement("Day Mode").equals("true"), "Test failed");

    }

    //Verify that "Night Mode" can be selected, the accessary shows "Night Mode"
    @Test
    public void selectNightModeTest() throws InterruptedException{

        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();

        ast.aRidioButtonTurnOff("Day Mode");
        ReusMeth.waiting(3);
        ast.aRadioButtonTurnOn("Night Mode");
        assertTrue(ast.getCheckedStatusUIElement("Night Mode").equals("true"), "Test failed");

    }

    //Verify that "Auto Mode" can be selected, the accessary shows relevant Mode
    @Test
    public void selectAutoModeTest() throws InterruptedException{

        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();

        ast.aRidioButtonTurnOff("Day Mode");
        ReusMeth.waiting(3);
        ast.aRadioButtonTurnOn("Auto");
        assertTrue(ast.getCheckedStatusUIElement("Auto").equals("true"), "Test failed");

    }

    //To automate
    //Verify that "Night Mode" can be selected, the accessary shows "Night Mode"
    //Verify that "Auto Mode" can be selected, the accessary shows relevant mode
    //Verify that night mode is turned on during night period (7:00PM to 6:59AM)
    //Verify that Display Later turns on night mode during night period (7:00PM to 6:59AM)
    //Verify that night mode is turned on from day mode on at 7:00PM
    //Verify that day mode is turned on from night mode at 7:00AM
    //Verify that Display Mode is set "Auto" by default when launching the app first time after the installation

}
