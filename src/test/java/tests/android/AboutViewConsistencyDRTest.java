//By Vadim
package tests.android;


import baseplatformANDROID.BaseTest;
import objects.android.AboutViewDR;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.Assert.*;
import utility.ReusMeth;


public class AboutViewConsistencyDRTest extends BaseTest {

    private MainViewDR mv;
    private MoreOptionMenuDR mom;
    private AboutViewDR aboutView;

    @BeforeClass
    public void setUpLaunching() throws InterruptedException{
        mv = new MainViewDR(driver);
        mom = new MoreOptionMenuDR(driver);
        aboutView = new AboutViewDR(driver);

        //mv.updateApplicationSignButtonOkTap();//turning off notification of updating if presented
        mv.moreOptionButtonTap();
        ReusMeth.waiting(5);
        mom.aboutButtonTap();

    }

    @Test
    public void headerOfAboutDR() throws IOException, InterruptedException {
        assertEquals(aboutView.getTextOfheaderDisplayRideAboutView(), "Display Ride");
    }

    @Test
    public void accessoryAppOfAboutDR() throws IOException, InterruptedException {
        assertEquals(aboutView.getTextOfheaderAccessoryAppAboutView(), "Accessory App");
    }

    @Test
    public void accessoryImageOfAboutDR() throws IOException, InterruptedException {
        assertTrue(aboutView.versionBuildAccessoryImageOfAboutViewIsPresented());
    }

    @Test
    public void versionBuildTextOfAboutDR() throws IOException, InterruptedException {
        assertTrue(aboutView.versionBuildAccessoryTextOfAboutViewIsPresented());
   }

    @Test
    public void batteryAccessoryImageOfAboutDR() throws IOException, InterruptedException {
        assertTrue(aboutView.getBatteryAccessoryImageOfAboutViewIsPresented());
    }

    @Test
    public void chargingTextOfAboutDR() throws IOException, InterruptedException {
        assertEquals(aboutView.chargingText(), "charging");
    }

    @Test
    public void chargingLevelTextOfAboutDR() throws IOException, InterruptedException {
        assertTrue(aboutView.chargingLevelTextIsPresented());
    }

    @Test
    public void driverAppOfAboutDR() throws IOException, InterruptedException {
        assertEquals(aboutView.headerDriverAppText(), "Driver App");
    }

    @Test
    public void versionBuildImageOfAboutDR() throws IOException, InterruptedException {
        assertTrue(aboutView.versionBuildDeviceImageOfAboutViewIsPresented());
    }

    @Test
    public void versionBuildDeviceOfAboutDR() throws IOException, InterruptedException {
        assertTrue(aboutView.versionBuildDeviceOfAboutViewIsPresented());
    }


    @Test
    public void oKButtonOfAboutDR() throws IOException, InterruptedException {
        assertTrue(aboutView.okButtonIsPresented());
    }

    @AfterClass
    public void tearDown() throws InterruptedException {

        driver.closeApp();

    }

}
