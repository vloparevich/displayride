package tests.main_view_partner;


import baseplatformANDROID.BaseTest;
import baseplatform.DataClass;
import objects.android.MainViewDR;
import objects_partner.MainViewPartner;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
/*
by Sergey
*/
public class SendTextToAccessory extends BaseTest {

    private MainViewPartner mvp;
    private MainViewDR mv;

    String testName = null;


    @BeforeClass
    public void setUpLaunching() {

        testName = this.getClass().getSimpleName();
        mvp = new MainViewPartner(driver);
        mv = new MainViewDR(driver);
    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
        driver.launchApp();
    }

    @AfterMethod
    public void onFinishingMethodWithinCLass() {
        driver.closeApp();
        
    }


    @Test(invocationCount = 3, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
    public void verifySendTextToAccessory(String name) throws IOException, InterruptedException {

        mvp.inputTextField(name);
        mvp.sendTextButtonTap();

        ReusMeth.changeActivityToDisplayRideApp();

        assertEquals(mv.waitUntilParticularTextPresented(name), name);
    }

    @Test(invocationCount = 3, dataProvider = "riderName-Provider-3", dataProviderClass = DataClass.class)
    public void verifySendSpeshialTextToAccessory(String name) throws IOException, InterruptedException {

        mvp.inputTextField(name);
        mvp.sendTextButtonTap();

        ReusMeth.changeActivityToDisplayRideApp();

        assertEquals(mv.waitUntilParticularTextPresented(name), name);
    }



    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);

    }


}
