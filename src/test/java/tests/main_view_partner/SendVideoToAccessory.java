package tests.main_view_partner;


import baseplatformANDROID.BaseTest;
import baseplatform.DataClass;
import objects_partner.MainViewPartner;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;
/*
by Sergey
*/
public class SendVideoToAccessory extends BaseTest {

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


    @Test(invocationCount = 3, dataProvider = "FTP_Video_Provider", dataProviderClass = DataClass.class)
    public void verifySendImageToAccessory(String ftp_video, int pause) throws IOException, InterruptedException {

        mvp.inputFieldFTP(ftp_video);
        mvp.displayVideoTap();
        ReusMeth.waiting(pause);

        //TODO Add Assert for Toas Messsage
    }

    @AfterClass
    public void tearDown() throws InterruptedException {

        ReusMeth.waiting(5);

    }


}
