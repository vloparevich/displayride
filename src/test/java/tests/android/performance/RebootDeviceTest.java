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

public class RebootDeviceTest extends BaseTest {

    private MainViewDR mv;
    private SettingsViewDR setView;
    String testName = null;
    private DesiredCapabilities cap;

    @BeforeClass
    public void settingClassSettings() {
        setView = new SettingsViewDR(driver);
        setView.navigateToMainViewForUberAndLyftIfneeds();

    }

    @Parameters({"browserName", "deviceName", "udid", "platformName", "appPackage",
            "appActivity", "newCommandTimeout", "unicodeKeyboard",
            "resetKeyboard", "fullReset", "noReset", "portNumber", "accessoryId"})
    @BeforeMethod
    public void onStartingMethodWithinClass(String browserValue, String deviceNameValue, String uniqueDeviceIdValue,
                                 String platformNameValue, String appPackValue, String appActivityValue,
                                 String newCommandTimeoutValue, String unicodeKeyBoardValue, String resetKeyBoardValue,
                                 String fullResetValue, String noResetValue, String portNumberValue, String accessoryId) throws Exception {

        Runtime.getRuntime().exec(ADB + " -s " + uniqueDeviceIdValue + " reboot");

        Thread.sleep(75000);

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

        testName = this.getClass().getSimpleName();
        mv = new MainViewDR(driver, 65);

    }

    @Test(invocationCount = 1, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
    public void rebootingDevice(String riderNameValue) {
        mv.inputNameIntoField(riderNameValue);
        mv.displayNowTap();
        mv.gettingNameFromNameField();
        mv.getContentFromTextPreviewFrameArray();
        assertTrue(mv.verifyNameIsPresentedInMessageFrame(), "The connection is not established, name is not transmitted.");

    }
}



