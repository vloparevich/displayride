package tests.ios;

import baseplatform.DataClass;
import baseplatformIOS.BaseTest;


import objects.ios.MainViewDR;

import objects.ios.SettingsViewDR;
import objects.ios.popupmenus.PopUpMenuOfStopDisplayButtonDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class MainViewDRTest extends BaseTest {

    private MainViewDR mv;
    private PopUpMenuOfStopDisplayButtonDR pm;
    private SettingsViewDR setView;

    String testName = null;


    @BeforeClass
    public void setUpLaunching() {

        testName = this.getClass().getSimpleName();

        mv = new MainViewDR(idriver);
        pm = new PopUpMenuOfStopDisplayButtonDR(idriver);

        setView = new SettingsViewDR(idriver);

        setView.navigateToMainViewForUberIfneeds();

    }

    @AfterMethod
    public void relaunchApp() throws InterruptedException {
        idriver.launchApp();
        Thread.sleep(3000);
    }

    @Test(invocationCount = 1, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
    public void textIsDisplayedAreYouSure(String riderNameValue) {

        mv.inputNameIntoField(riderNameValue);
        mv.displayNowTap();
        //TODO attribute for the text's value is missing, should be added in order to retrieve the content from the preview frame
        //mv.gettingNameFromNameField();
        //mv.getContentFromTextPreviewFrameArray();//attribute for the text value is missing
        //assertTrue(mv.verifyNameIsPresentedInMessageFrame(), "The name is displayed Incorrectly");//
        mv.stopDisplayTap();

        // TODO figure out TestNG' issue related to assertTrue verification

        assertEquals(pm.areYouSureText(), "Are you sure ?");

    }

    @Test(invocationCount = 1, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
    public void textIsDisplayedStopDisplay(String riderNameValue) {

        mv.inputNameIntoField(riderNameValue);
        mv.displayNowTap();
        mv.stopDisplayTap();
        assertEquals(pm.stopDisplayText(), "Stop Display");

    }

    @AfterClass
    public void tearDown() {

    }

}
