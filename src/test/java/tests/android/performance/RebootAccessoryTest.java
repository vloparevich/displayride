package tests.android.performance;

import baseplatform.DataClass;
import baseplatformANDROID.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import objects.android.MainViewDR;
import objects.android.SettingsViewDR;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class RebootAccessoryTest extends BaseTest {

    private MainViewDR mv;
    private SettingsViewDR setView;
    private DesiredCapabilities cap;


    @Parameters({"browserName", "deviceName", "udid", "platformName", "appPackage",
            "appActivity", "newCommandTimeout", "unicodeKeyboard",
            "resetKeyboard", "fullReset", "noReset", "portNumber", "accessoryId"})
    @BeforeMethod
    public void onStartingMethodWithinClass(String browserValue, String deviceNameValue, String uniqueDeviceIdValue,
                                            String platformNameValue, String appPackValue, String appActivityValue,
                                            String newCommandTimeoutValue, String unicodeKeyBoardValue, String resetKeyBoardValue,
                                            String fullResetValue, String noResetValue, String portNumberValue, String accessoryId) throws Exception {

        Runtime.getRuntime().exec(ADB + " -s " + accessoryId + " reboot");
        Thread.sleep(85000);

        cap = new DesiredCapabilities();
        cap.setCapability("browsername", browserValue);
        cap.setCapability("deviceName", deviceNameValue);
        cap.setCapability("udid", uniqueDeviceIdValue);
        cap.setCapability("platformName", platformNameValue);
        cap.setCapability("appPackage", appPackValue);
        cap.setCapability("appActivity", appActivityValue);
        cap.setCapability("newCommandTimeout", newCommandTimeoutValue);
        cap.setCapability("unicodeKeyboard", unicodeKeyBoardValue);
        cap.setCapability("resetKeyboard", resetKeyBoardValue);
        cap.setCapability("fullReset", fullResetValue);
        cap.setCapability("noReset", noResetValue);

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:" + portNumberValue + "/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        mv = new MainViewDR(driver, 65);
        setView = new SettingsViewDR(driver);
        setView.navigateToMainViewForUberAndLyftIfneeds();

    }

    @Test(invocationCount = 1, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
    public void rebootingAccessoryTest(String riderNameValue) {
        mv.inputNameIntoField(riderNameValue);
        mv.displayNowTap();
        mv.gettingNameFromNameField();
        mv.getContentFromTextPreviewFrameArray();
        assertTrue(mv.verifyNameIsPresentedInMessageFrame(), "The connection is not established after relaunching Accessory, name is not transmitted.");

    }
}
