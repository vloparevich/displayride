package verifying_purposes;


import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import objects.ios.MoreOptionMenuDR;
import objects.ios.SettingsViewDR;
import objects.ios.popupmenus.PopUpMenuOfStopDisplayButtonDR;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import utility.ReusMeth;

import static baseplatformANDROID.BaseTest.driver;
import static java.lang.System.getProperty;
import static utility.ReusMeth.logPath;
import static utility.ReusMeth.timeStampShort;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ForVerifyingPurposesVadim {

    private DesiredCapabilities cap;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    public DesiredCapabilities appiumcaps;

    private String currentDir = System.getProperty("user.dir");
    private File interMediateDir = new File(currentDir, "IOS_APP/Payload");

    private String projectPath = interMediateDir.getAbsolutePath();
    private File app = new File(projectPath, "DisplayRideIOS.app");

    private String fullPath = app.getAbsolutePath();

    public static IOSDriver<MobileElement> idriver = null;

    String testName = null;

    private By displayNowButton = By.id("displayNowButton");//+
    private By displayLaterButton = By.id("displayLaterButton");//+
    private By editNameField = By.xpath("//XCUIElementTypeTextField[@value='Add Name']");//+
    // private By editNameField = By.id("EnterRiderNameTextField");//+
    private By carrierExpandButton = By.xpath("//XCUIElementTypeStaticText[@name=\"carServiceNameLabel\"]");//+
    private By messageFrame = By.xpath("//XCUIElementTypeStaticText[@value=\"statusLabel\"]");
    //private By snackBarText = By.id("snackbar_text");//notification about updating accessory
    protected By editNameHint = By.xpath("//*[@value = 'Add Name']");
    protected By stopDisplayButtonDR = By.xpath("//XCUIElementTypeButton[@name='displayNowButton']");
    protected By moreOptionButton = By.id("menuButton");

    private MoreOptionMenuDR mop;
    private SettingsViewDR setView;
    private PopUpMenuOfStopDisplayButtonDR pm;


    @BeforeClass
    public void launchingAndroidDriver() throws IOException {

        cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "iPhone s1");
        cap.setCapability("platformName", "iOS");
        cap.setCapability("platformVersion", "11.3");
        cap.setCapability("udid", "970ee7e4ff5581ab909700b61bd6810fec4fd720");
        cap.setCapability("appiumVersion", "1.8.1");
        cap.setCapability("automationName", "XCUITest");
        cap.setCapability("wdaLocalPort", "8500");
        cap.setCapability("noReset", "true");
        cap.setCapability("fullReset", "false");
        cap.setCapability("newCommandTimeout", "3600");//has to be integer
        cap.setCapability("app", fullPath);

        idriver = new IOSDriver(new URL("http://127.0.0.1:4724/wd/hub"), cap);

        idriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        setView = new SettingsViewDR(idriver);
        mop = new MoreOptionMenuDR(idriver);
    }


    @Test
    public void testings() throws IOException, InterruptedException {

       // ReusMeth.waiting(5);

        /*File src = ((TakesScreenshot) idriver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(src, new File(logPath(testName + "_Screenshot" + timeStamp() + ".png", testName)));

        //FileUtils.copyFile(src, new File(logPath(testName) +  "_" + timeStampShort() + ".png"));
        FileUtils.copyFile(src, new File(logPath(testName) + timeStampShort() + ".png"));*/
       // idriver.findElement(editNameField).click();
        //idriver.findElement(editNameField).sendKeys("Hello World");
      //  String holder = idriver.findElement(editNameField).getAttribute("value");



        //System.out.println(idriver.getTitle());
       //idriver.findElement(carrierExpandButton).click();

        //String frameText =  idriver.findElement(messageFrame).getText();

        //System.out.println(holder + " " + frameText);

       /* try {
            idriver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Status-OK " + e + "- No Keyboard is presented");
        }
        System.out.println("This is working");
*/

        idriver.findElement(moreOptionButton).click();
       mop.settingsButtonTap();


        System.out.println(idriver.findElement(setView.getUberButton()).getText());

        System.out.println(idriver.getPageSource());

    }

    @AfterClass
    public void slowClosing() throws InterruptedException {


    }

}
