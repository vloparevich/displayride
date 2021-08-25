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
public class SendDemoToAccessory extends BaseTest {

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


    @Test(invocationCount = 3, dataProvider = "FTP_Demo_Provider", dataProviderClass = DataClass.class)
    public void verifySendImageToAccessory(String ftpDemo, int pause) throws IOException, InterruptedException {

        mvp.inputFieldFTP(ftpDemo);
        mvp.demoModeButtonTap();

        ReusMeth.waiting(pause);
        //TODO Add Assert
    }

    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);

    }


}
