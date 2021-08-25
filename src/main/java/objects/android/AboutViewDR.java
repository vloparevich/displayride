//By Vadim
package objects.android;

import baseclassANDROID.BaseViewSuperClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AboutViewDR extends BaseViewSuperClass {

    private By headerDisplayRide = By.xpath("//android.widget.TextView[@text ='Display Ride']");
    private By headerAccessoryApp = By.xpath("//android.widget.TextView[@text ='Accessory App']");
    private By versionBuildAccessory = By.id("tvVersion");
    private By versionBuildImageAccessory = By.id("imgVersion");
    private By batteryImageAccessory = By.id("imgBattery");
    private By chargingText = By.xpath("//android.widget.TextView[@text = 'charging']");
    private By chargingLevelText = By.xpath("//android.widget.TextView[contains(@text, '%')]");
    private By headerDriverAppText = By.xpath("//android.widget.TextView[@text = 'Driver App']");
    private By okButton = By.xpath("//android.widget.TextView[@text = 'OK']");
    private By versionBuildImageDevice = By.id("imgVVersion");
    private By versionBuildDevice = By.id("tvRVersion");
    private By batteryLevel = By.id("tvBattery");

    public AboutViewDR(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void toTest() {
    	WebElement element = driver.findElement(batteryLevel);
    	WebElement el2 = element.findElement(chargingLevelText);
    	
    }
    
    public String getTextOfheaderDisplayRideAboutView() {
        wait.until(ExpectedConditions.presenceOfElementLocated(headerDisplayRide));
        String headerText = driver.findElement(headerDisplayRide).getAttribute("text");

        return headerText;
    }

    public String getTextOfheaderAccessoryAppAboutView() {
        wait.until(ExpectedConditions.presenceOfElementLocated(headerAccessoryApp));
        String headerText = driver.findElement(headerAccessoryApp).getAttribute("text");

        return headerText;
    }

    public boolean versionBuildAccessoryTextOfAboutViewIsPresented() {
        wait.until(ExpectedConditions.presenceOfElementLocated(versionBuildAccessory));
        return driver.findElements(versionBuildAccessory).size() > 0;
    }

    public boolean versionBuildAccessoryImageOfAboutViewIsPresented() {
        wait.until(ExpectedConditions.presenceOfElementLocated(versionBuildImageAccessory));
        return driver.findElements(versionBuildImageAccessory).size() > 0;
    }


    public boolean getBatteryAccessoryImageOfAboutViewIsPresented() {
        wait.until(ExpectedConditions.presenceOfElementLocated(batteryImageAccessory));
        return driver.findElements(batteryImageAccessory).size() > 0;
    }


    public boolean chargingStatus() {
        wait.until(ExpectedConditions.presenceOfElementLocated(chargingText));
        String chargingTextValue = driver.findElement(chargingText).getAttribute("text");

        String batteryLevelString = driver.findElement(batteryLevel).getText().split(" ")[0];
        int batteryLevel = Integer.parseInt(batteryLevelString);

        if (batteryLevel == 100 && chargingTextValue.equals("not charging")) {
            return true;
        } else if (batteryLevel < 100 && chargingTextValue.equals("charging")) {
            return true;
        } else {
            return false;
        }
    }


    public String chargingText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(chargingText));
        String chargingTextValue = driver.findElement(chargingText).getAttribute("text");

        return chargingTextValue;
    }


    public boolean versionBuildDeviceImageOfAboutViewIsPresented() {
        wait.until(ExpectedConditions.presenceOfElementLocated(versionBuildImageDevice));
        return driver.findElements(versionBuildImageDevice).size() > 0;
    }

    public String chargingLevelText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(chargingLevelText));
        String chargingLevelTextValue = driver.findElement(chargingLevelText).getAttribute("text");

        return chargingLevelTextValue;
    }

    public boolean chargingLevelTextIsPresented() {
        wait.until(ExpectedConditions.presenceOfElementLocated(chargingLevelText));
        return driver.findElements(chargingLevelText).size() > 0;
    }

    public String headerDriverAppText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(headerDriverAppText));
        String driverAppText = driver.findElement(headerDriverAppText).getAttribute("text");

        return driverAppText;
    }

    public boolean versionBuildDeviceOfAboutViewIsPresented() {
        wait.until(ExpectedConditions.presenceOfElementLocated(versionBuildDevice));
        return driver.findElements(versionBuildDevice).size() > 0;
    }

    public void okTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(okButton));
        t.tap(driver.findElement(okButton)).perform();

    }

    public boolean okButtonIsPresented() {

        wait.until(ExpectedConditions.presenceOfElementLocated(okButton));

        return driver.findElements(okButton).size() > 0;

    }

}
