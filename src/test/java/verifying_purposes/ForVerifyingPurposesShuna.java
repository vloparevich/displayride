package verifying_purposes;

import baseplatform.DataClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.ReusMeth;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

import objects.android.MainViewDR;
import objects.android.SettingsViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.PopUpMenuCarriersMainViewDR;

public class ForVerifyingPurposesShuna {

    public AndroidDriver<AndroidElement> driver = null;
    private DesiredCapabilities cap;
    private String pomDirectory = "/Users/displayride/TestDisplayRideAutomation/DisplayRideAutomation/DisplayRidePom";
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities appiumcaps;


    private By displayNowButton = By.id("btnDisplayNow");
    private By displayLaterButton = By.id("btnDisplayLater");
    private By editNameField = By.id("editName");
    private By carrierExpandButton = By.xpath("cabName");
    private By messageFrame = By.id("txtPreview");
    protected By stopDisplayButtonDR = By.xpath("//android.widget.TextView[@text = 'Stop Display']");
    protected By moreOptionButton = By.xpath("//*[@content-desc = 'More options']");
    protected By settingsOptionButton = By.xpath("//*[@text = 'Settings']");
    protected By carriersList = By.xpath("//android.widget.CheckBox[@resource-id='displayride.displayrideandroidapp:id/checkUber']");
    protected By checkedNum = By.xpath("//*[@checked = 'true']");
    protected By carrierListThroughandroidwidgetCheckBox =By.xpath("//android.widget.CheckBox");

    protected By carriersListMatch = By.xpath("//android.widget.CheckBox[starts-with(@resource-id, 'displayride.displayrideandroidapp:id/check')]");
//*[starts-with(@id, 'sometext') and ends-with(@id, '_text')]
// *[matches(@id, 'displayride.displayrideandroidapp\d+_text')]


    String testName = null;

    //testName = this.getClass().getSimpleName();

    @BeforeClass
    public void launchingAndroidDriver() throws MalformedURLException {

        cap = new DesiredCapabilities();
        cap.setCapability("browsername", "chrome");
        cap.setCapability("deviceName", "My Nougat-2");
        cap.setCapability("udid", "1100021017263832"); //TODO Add UDID
        cap.setCapability("platformName", "Android");
        cap.setCapability("appPackage", "displayride.displayrideandroidapp");
        cap.setCapability("appActivity", "MainActivity");

        //managing how long Appium will wait for a new command from the client(by default it's 60sec)
        cap.setCapability("newCommandTimeout", "900");
        cap.setCapability("unicodeKeyboard", "true");
        cap.setCapability("resetKeyboard", "false");
        cap.setCapability("fullReset", "false");
        cap.setCapability("noReset", "true");
        //Session override is a server flag, not a desired capability, so desired_caps['session-override'] = True does nothing.
        //cap.setCapability("--session-override", true);

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);


        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "carServiceName-1", dataProviderClass = DataClass.class,
            description = "Verify that the \"Add Car Service\" field on main screen accepts case sensitive chars, " +
                    "Verify that the \"Add Car Service\" field on main screen supports multilanguage," +
                    "Verify that \"Add Car Service\" field on main screen accepts digits," +
                    "Verify that \"Add Car Service\" field on main screen accepts special chars," +
                    "Verify that the \"Add Car Service\" field on main screen accepts emoji," +
                    "Verify that the \"Add Car Service\" field on main screen accepts case sensitive chars, digits, special chars, emoji and double byte chars," +
                    "Verify that the \"Add Car Service\" field on main accepts 12 chars the most")

    public void testings(String riderNameValue) throws InterruptedException {

        MainViewDR mv = new MainViewDR(driver);
        SettingsViewDR st = new SettingsViewDR(driver);
        MoreOptionMenuDR mom = new MoreOptionMenuDR(driver);
        PopUpMenuCarriersMainViewDR popm = new PopUpMenuCarriersMainViewDR(driver);


        String theName = null;

        mv.moreOptionButtonTap();
        mom.settingsButtonTap();

        st.inputCabServiceIntoField(riderNameValue);
        st.continueTap();
        ReusMeth.waiting(5);

        mv.selectCarrierExpandableField();

        assertTrue(popm.verifyCabServiceIsPresentedInPopupMenu(riderNameValue), "Car Service was not added successfully!!!");

        popm.tapOnParticularCarrier(riderNameValue);

    }

    @AfterClass
    public void slowClosing() throws InterruptedException {

    }

}
