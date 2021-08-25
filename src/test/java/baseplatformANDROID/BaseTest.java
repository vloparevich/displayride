//By Vadim
package baseplatformANDROID;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import static utility.ReusMeth.*;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static java.lang.System.getProperty;

public class BaseTest {

    public static AndroidDriver<AndroidElement> driver = null;
    private DesiredCapabilities cap;
    private String pomDirectory = "/Users/displayride/TestDisplayRideAutomation/DisplayRideAutomation/DisplayRidePom";
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities appiumcaps;
    public static String ADB = System.getenv("ANDROID_HOME") + "/platform-tools/adb";

    @Parameters({"portNumber"})
    @BeforeSuite
    public void baseTestSetUp(String portNumberValue) throws IOException, InterruptedException {

        System.out.println("The time when the suite was started is: " + timeStampLong());

        appiumcaps = new DesiredCapabilities();
        appiumcaps.setCapability("noReset", "false");

        //Build the Appium service
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(Integer.parseInt(portNumberValue));
        builder.withCapabilities(appiumcaps);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();

        //Also for starting appium server can be used the command for terminal (not stable)
        //Runtime.getRuntime().exec("appium -p " + portNumberValue);

        System.out.println("appium -p " + portNumberValue + " is launching...");
        //Wait for appium for 15 seconds
        //Thread.sleep(15000L);

        //FULL RESET: FULL_RESET true / NO_RESET false
        //FAST RESET: FULL_RESET false / NO_RESET false
        //JUST STARTING FROM THE LAST POINT: FULL_RESET false / NO_RESET true

    }

    @Parameters({
            "browserName", "deviceName", "udid", "platformName", "appPackage",
            "appActivity", "newCommandTimeout", "unicodeKeyboard",
            "resetKeyboard", "fullReset", "noReset", "portNumber", "accessoryId"
    })
    @BeforeClass
    public void launchingAndroidDriver(String browserValue, String deviceNameValue, String uniqueDeviceIdValue,
                                       String platformNameValue, String appPackValue, String appActivityValue,
                                       String newCommandTimeoutValue, String unicodeKeyBoardValue, String resetKeyBoardValue,
                                       String fullResetValue, String noResetValue, String portNumberValue, String accessoryId) throws Exception {

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

        Runtime.getRuntime().exec(ADB + " -s " + uniqueDeviceIdValue + " logcat -c");

        waiting(2);

        //System.out.println("Device udid: " + deviceUdid());
        //System.out.println("Accessory udid: " + accessoryUdid());

    }

    @Parameters({"udid"})
    @BeforeMethod
    public void cleanLognDevice(String uniqueDeviceIdValue) throws IOException {
        Runtime.getRuntime().exec(ADB + " -s " + uniqueDeviceIdValue + " logcat -c");
    }

    @AfterClass
    public void baseTearDown() throws InterruptedException {

        Thread.sleep(1000);

        try {
            driver.close();
        } catch (Exception e) {
            System.out.println("The app is already closed!!");
        }

    }

    @AfterSuite
    public void basicSettings() {
        //driver.quit();
        driver = null;
        //stopping appium server
        service.stop();
        //Runtime.getRuntime().exec("killall node");

    }


}
