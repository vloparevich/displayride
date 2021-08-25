package tests.android.appsettingsview;

import baseplatformANDROID.BaseTest;
import objects.android.AppSettingsViewDR;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ReusMeth;

import static org.testng.Assert.assertTrue;

public class AppSettingsAdModeTest extends BaseTest {

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

    //Verify that Advertisement Mode can be turned on
    @Test
    public void advertisementModeOnTest() throws InterruptedException{
/*
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() +
                " -----> " + Thread.currentThread().getStackTrace()[1].getMethodName() +
                " --- Test Count = " + testCount + "\033[0m");
*/

        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();

        ast.aRidioButtonTurnOff("Advertisement Mode");
        ReusMeth.waiting(5);
        ast.aRadioButtonTurnOn("Advertisement Mode");
        assertTrue(ast.getCheckedStatusUIElement("Advertisement Mode").equals("true"), "Test failed");

    }

    //Verify that Advertisement Mode can be turned off
    @Test
    public void dvertisementMModeOffTest() throws InterruptedException{

        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();

        ast.aRadioButtonTurnOn("Advertisement Mode");
        ReusMeth.waiting(3);
        ast.aRidioButtonTurnOff("Advertisement Mode");
        assertTrue(ast.getCheckedStatusUIElement("Advertisement Mode").equals("false"), "Test failed");

    }


    //Verify that "Google" can be selected
    @Test
    public void selectGoogleModeTest() throws InterruptedException{

        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();

        ast.aRidioButtonTurnOff("Self");
        ReusMeth.waiting(3);
        ast.aRadioButtonTurnOn("Google");
        assertTrue(ast.getCheckedStatusUIElement("Google").equals("true"), "Test failed");

    }

    //Verify that "Partner" can be selected
    @Test
    public void selectPartnerModeTest() throws InterruptedException{


        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();

        ast.aRidioButtonTurnOff("Self");
        ReusMeth.waiting(3);
        ast.aRadioButtonTurnOn("Partner");
        assertTrue(ast.getCheckedStatusUIElement("Partner").equals("true"), "Test failed");

    }

    //Verify that "Self" can be selected
    @Test
    public void selectSelfModeTest() throws InterruptedException{


        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();

        ast.aRidioButtonTurnOff("Partner");
        ReusMeth.waiting(3);
        ast.aRadioButtonTurnOn("Self");
        assertTrue(ast.getCheckedStatusUIElement("Self").equals("true"), "Test failed");

    }

    //To automate
    //Verify that Advertisement Mode is set to "Self" by default when launching the app first time after the installation
 }
