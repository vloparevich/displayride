//By Vadim
package tests.android;

import baseplatformANDROID.BaseTest;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static utility.ReusMeth.waiting;


public class FloatingWidgetTest extends BaseTest {


    public static AndroidDriver<AndroidElement> driverUber = null;
    public static DesiredCapabilities cap2;
    public String portNumberValue = null;
    public String appPath = "/Users/displayride/AutomationTestNG/DisplayRideAutomation/DisplayRidePom";


    @Parameters({"portNumber"})
    @BeforeClass
    protected void portNumberForUber(String portNumberValue) {
        this.portNumberValue = portNumberValue;
    }

    /*private String pomDirectory = "/Users/displayride/TestDisplayRideAutomation/DisplayRideAutomation/DisplayRidePom";

    public static String ADB = System.getenv("ANDROID_HOME") + "/platform-tools/adb";*/

    protected AndroidDriver<AndroidElement> driverUber() throws IOException, InterruptedException {

        File app = new File(appPath, "com.ubercab_4.213.10001.apk");
        String fullPath = app.getAbsolutePath();


        cap2 = new DesiredCapabilities();
        cap2.setCapability("browsername", "chrome");
        cap2.setCapability("deviceName", "My Nougat-2");
        //cap.setCapability("udid", uniqueDeviceIdValue);
        cap2.setCapability("platformName", "Android");
        cap2.setCapability("app", fullPath);
        //cap2.setCapability("appPackage", "com.ubercab");
        //cap2.setCapability("appActivity", "RootActivity");

        /*cap2.setCapability("newCommandTimeout", "3600");
        cap2.setCapability("unicodeKeyboard", "true");
        cap2.setCapability("resetKeyboard", "false");
        cap2.setCapability("fullReset", "false");
        cap2.setCapability("noReset", "true");*/

        driverUber = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:" + portNumberValue + "/wd/hub"), cap2);

        driverUber.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        waiting(2);
        return driverUber;

    }
    
    @Test
    public void startActivity() throws InterruptedException {


// App1 capabilities
        String displayRideAppPackageNAme = "com.displayride.displayrideandroidapp";
        String displayRideAppAppActivityName = "com.displayride.displayrideandroidapp.MainActivity";

// App2 capabilities
        String uberAppPackageName = "com.ubercab";
        String uberAppActivityName = "com.ubercab.presidio.app.core.root.RootActivity";

    /*    driver.startActivity(settingsAppPackageName, settingsAppActivityName);

        //Switch OFF WIFI

        //Re launch calculator App
        driver.startActivity(calculatorAppPackageName, calculatorAppActivityName);*/

        Activity activity = new Activity(uberAppPackageName, uberAppActivityName);

        activity.setAppWaitPackage(uberAppPackageName);
        activity.setAppWaitActivity(uberAppActivityName);

        activity.setStopApp(false);
        ((AndroidDriver) driver).startActivity(activity);

        System.out.println("Wait for 8 sec and switch to activity once again of Uber");
        Thread.sleep(8000);

        driver.findElementByAndroidUIAutomator("text(\"Work\")").click();

        //driver.findElement(By.xpath("//*[@text = 'Work']")).click();
        //driver.findElementByAndroidUIAutomator("new UiSelector().text(Work)").click();
    }

}
