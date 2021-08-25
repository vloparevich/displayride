package tests.main_view_partner;


import baseplatformANDROID.BaseTest;
import baseplatform.DataClass;
import objects_partner.MainViewPartner;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
/*
by Sergey
*/
public class UiElementsTextConsistencyTest extends BaseTest {

    private MainViewPartner mvp;

    String testName = null;


    @BeforeClass
    public void setUpLaunching() {

        testName = this.getClass().getSimpleName();
        mvp = new MainViewPartner(driver);
    }


    @BeforeMethod
    public void onStartingMethodWithinClass() {
        driver.launchApp();
    }


    @AfterMethod
    public void onFinishingMethodWithinCLass() {
        driver.closeApp();
    }


    @Test
    public void verifyUiElementsTextConsistency() throws IOException, InterruptedException {

        //Check Text on the Display Image Button
        assertEquals(mvp.getTextDisplayImageButton(), "Display Image");

        //Check Text on the Display Video Button
        assertEquals(mvp.getTextDisplayVideoButton(),"Display Video");

        //Check Text on the Display OFF Button
        assertEquals(mvp.getTextDisplayOffButton(),"Display OFF");

        //Check Text on the Send Text Button
        assertEquals(mvp.getTextFromSendTextButton(),"Send Text");
    }


    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);

    }







}
