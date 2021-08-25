package tests.main_view_partner;


import baseplatformANDROID.BaseTest;
import baseplatform.DataClass;
import objects_partner.MainViewPartner;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;

import static org.testng.Assert.assertTrue;
/*
by Sergey
*/
public class SendImageToAccessory extends BaseTest {

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


    @Test(invocationCount = 3, dataProvider = "FTP_Images_Provider", dataProviderClass = DataClass.class)
    public void verifySendImageToAccessory(String ftp_image, int pause) throws IOException, InterruptedException {

        mvp.inputFieldFTP(ftp_image);
        mvp.displayImageTap();
        ReusMeth.waiting(pause);

        //TODO Add Assert for Toas Messsage
    }

    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);

    }


}
