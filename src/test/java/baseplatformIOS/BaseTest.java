//By Vadim
package baseplatformIOS;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import static utility.ReusMeth.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


public class BaseTest {

    private DesiredCapabilities cap;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    public DesiredCapabilities appiumcaps;

    private String currentDir = System.getProperty("user.dir");
    private File interMediateDir = new File(currentDir, "IOS_APP/Payload");

    private String projectPath = interMediateDir.getAbsolutePath();
    private File app = new File(projectPath, "DisplayRideIOS.app");

    private String fullPath = app.getAbsolutePath();

    public static IOSDriver idriver = null;
    private WebDriverWait waitAlert;


    @Parameters({"portNumber"})
    @BeforeSuite
    public void baseTestSetUp(String portNumberValue) {

        System.out.println("The time when the suite was started for iOS is: " + timeStampLong());

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

        service = AppiumDriverLocalService.buildService(builder);

        service.start();

        System.out.println("appium -p " + portNumberValue + " is launching for iOS device...");

    }

    @Parameters({
            "deviceName", "platformName", "platformVersion", "udid", "appiumVersion", "automationName",
            "wdaLocalPort", "noReset", "fullReset", "newCommandTimeout", "resetKeyboard", "portNumber"
    })
    @BeforeClass
    public void launchingIOSDriver(String deviceNameValue, String platformNameValue, String platformVersionValue, String udidValue,
                                   String appiumVersionValue, String automationNameValue,
                                   String wdaLocalPortValue, String noResetValue, String fullResetValue,
                                   String newCommandTimeOutValue, String resetKeyboardValue, String portNumberValue) throws Exception {

        cap = new DesiredCapabilities();
        cap.setCapability("deviceName", deviceNameValue);
        cap.setCapability("platformName", platformNameValue);
        cap.setCapability("platformVersion", platformVersionValue);
        cap.setCapability("udid", udidValue);
        cap.setCapability("appiumVersion", appiumVersionValue);
        cap.setCapability("automationName", automationNameValue);
        cap.setCapability("wdaLocalPort", wdaLocalPortValue);
        cap.setCapability("noReset", noResetValue);
        cap.setCapability("fullReset", fullResetValue);
        cap.setCapability("newCommandTimeout", newCommandTimeOutValue);//has to be integer
        cap.setCapability("app", fullPath);

        idriver = new IOSDriver(new URL("http://127.0.0.1:" + portNumberValue + "/wd/hub"), cap);
        //implicit wait
        idriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            //explicit wait for alerts and following accepting them
            waitAlert = new WebDriverWait(idriver, 15);
            waitAlert.until(ExpectedConditions.alertIsPresent());
            idriver.switchTo().alert().accept();
        } catch (WebDriverException e) {
            System.out.println("The alert was not presented. Exception caught " + e);
        }

    }

    public static IOSDriver getIdriver() {
        return idriver;
    }

    @AfterTest
    public void closeAppAfterTest() throws InterruptedException {

        Thread.sleep(1000);

        try {
            idriver.close();
        } catch (Exception e) {
            System.out.println("The app is already closed!!");
        }
    }

    @AfterClass
    public void baseTearDown() throws IOException {
        /*System.out.println("Trying to take a screenshoot");
        File src = ((TakesScreenshot) getIdriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(logPath("/Users/displayride/Desktop/reports/methodName-screenshot.png")));
*/
    }

    @AfterSuite
    public void basicSettings() {

        service.stop();


    }


}



