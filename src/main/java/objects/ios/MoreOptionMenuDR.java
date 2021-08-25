package objects.ios;

import baseclassIOS.BaseViewSuperClass;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class MoreOptionMenuDR extends BaseViewSuperClass {

    //All More Options Menu Buttons
    private By allMoreOptionsButtons = By.className("android.widget.TextView");

    //Locators for More options menu buttons
    protected By settingsButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Settings']");
    protected By aboutButton = By.xpath("//android.widget.TextView[@resource-id = 'displayride.displayrideandroidapp:id/title' and @text = 'About']");

    //Locators for More Options menu from the Settings Page
    protected By resetAppButton = By.xpath("//android.widget.TextView[@resource-id = 'displayride.displayrideandroidapp:id/title' and @text = 'Reset App']");


    //Constructor
    public MoreOptionMenuDR(IOSDriver idriver) {

        super(idriver);
    }


    //Get ArrayList with all Buttons
    public List getMoreMenuButtons() {

        return idriver.findElements(allMoreOptionsButtons);
    }


    public void settingsButtonTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(settingsButton));
        t.tap(idriver.findElement(settingsButton)).perform();
    }

    //Tap on the About Button
    public void aboutButtonTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(aboutButton));
        t.tap(idriver.findElement(aboutButton)).perform();
    }

    //Tap on the Reset App Button
    public void resetAppButtonTap() {
        wait.until(ExpectedConditions.presenceOfElementLocated(resetAppButton));
        t.tap(idriver.findElement(resetAppButton)).perform();
    }

    //TODO Desing method for get text from Settings

    //TODO Desing method for get text from About

    //TODO Desing method for tap on the Update Accessory

    //TODO Desing method for get text from Accessory

}
