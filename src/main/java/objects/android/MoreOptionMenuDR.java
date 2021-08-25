package objects.android;

import baseclassANDROID.BaseViewSuperClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class MoreOptionMenuDR extends BaseViewSuperClass {

    //All More Options Menu Buttons
    private By allMoreOptionsButtons = By.className("android.widget.TextView");

    //Locators for More options menu buttons
    protected By settingsButton = By.xpath("//android.widget.TextView[@resource-id = 'displayride.displayrideandroidapp:id/title' and @text = 'Driver Settings']");
    protected By appSettingsButton = By.xpath("//android.widget.TextView[@resource-id = 'displayride.displayrideandroidapp:id/title' and @text = 'App Settings']");
    protected By aboutButton = By.xpath("//android.widget.TextView[@resource-id = 'displayride.displayrideandroidapp:id/title' and @text = 'About']");

    //Locators for More Options menu from the Settings Page
    protected By resetAppButton = By.xpath("//android.widget.TextView[@resource-id = 'displayride.displayrideandroidapp:id/title' and @text = 'Reset App']");


    //Confirm for "Reset App", by Shuna
    private By resetAppYes = By.id("button1");

    //Decline for "Reset App", by Shuna
    private By resetAppNo = By.id("button2");

    //Constructor
    public MoreOptionMenuDR(AndroidDriver<AndroidElement> driver) {

        super(driver);
    }


    //Get ArrayList with all Buttons
    public List getMoreMenuButtons() {

        return driver.findElements(allMoreOptionsButtons);
    }


    public void settingsButtonTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(settingsButton));
        t.tap(driver.findElement(settingsButton)).perform();
    }

    //added By Shuna on 06/27/2018
    public void appSettingsButtonTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(appSettingsButton));
        t.tap(driver.findElement(appSettingsButton)).perform();
    }

    //Tap on the About Button
    public void aboutButtonTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(aboutButton));
        t.tap(driver.findElement(aboutButton)).perform();
    }

    //Tap on the Reset App Button
    public void resetAppButtonTap() {
        wait.until(ExpectedConditions.presenceOfElementLocated(resetAppButton));
        t.tap(driver.findElement(resetAppButton)).perform();
    }

    //Tap on Reset App Yes button, by Shuna on 06/20/2018
    public void resetAppButtonYesTap() {
        wait.until(ExpectedConditions.presenceOfElementLocated(resetAppYes));
        t.tap(driver.findElement(resetAppYes)).perform();
    }

    //Tap on Reset App No button, by Shuna on 06/20/2018
    public void resetAppButtonNoTap() {
        wait.until(ExpectedConditions.presenceOfElementLocated(resetAppNo));
        t.tap(driver.findElement(resetAppNo)).perform();
    }

    //TODO Desing method for get text from Settings

    //TODO Desing method for get text from About

    //TODO Desing method for tap on the Update Accessory

    //TODO Desing method for get text from Accessory

}
