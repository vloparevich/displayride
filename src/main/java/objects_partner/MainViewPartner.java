package objects_partner;

import baseclassANDROID.BaseViewSuperClass;
import org.openqa.selenium.JavascriptExecutor;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainViewPartner extends BaseViewSuperClass {

    //Constructor
    public MainViewPartner(AndroidDriver<AndroidElement> driver) { super(driver); }

    //UI Buttons Locators
    private By sendTextButton = By.id("tvSendText");
    private By displayOffButton = By.id("tvDisplayCommand");
    private By displayImageButton = By.id("tvDisplayImage");
    private By displayVideoButton = By.id("tvDisplayVideo");
    private By demoModeButton = By.id("btDemo");

    //Input Locators
    private By inputTextField = By.id("etSendText");
    private By inputFieldFTP = By.id("etFileName");

    //TODO SL Notifications
    private By fileDownloading = By.xpath("//*[@text='File is downloading']");
    private By enterImageFileName = By.xpath("//*[@text='Please enter image file name']");

    //Send Text to the input Text Field
    public void inputTextField(String Text) {

        wait.until(ExpectedConditions.presenceOfElementLocated(inputTextField));
        driver.findElement(inputTextField).clear();
        driver.findElement(inputTextField).sendKeys(Text);

        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Status-OK " + e + "- No Keyboard is presented");
        }
    }


    //Send Text to the input FTP Text Field
    public void inputFieldFTP(String Text) {

        wait.until(ExpectedConditions.presenceOfElementLocated(inputFieldFTP));
        driver.findElement(inputFieldFTP).clear();
        driver.findElement(inputFieldFTP).sendKeys(Text);

        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Status-OK " + e + "- No Keyboard is presented");
        }
    }


    public void displayImageTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(displayImageButton));
        t.tap(driver.findElement(displayImageButton)).perform();
    }


    public void displayVideoTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(displayVideoButton));
        t.tap(driver.findElement(displayVideoButton)).perform();
    }


    public void demoModeButtonTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(demoModeButton));
        t.tap(driver.findElement(demoModeButton)).perform();
    }

    public void sendTextButtonTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(sendTextButton));
        t.tap(driver.findElement(sendTextButton)).perform();
    }


    public String getTextFromSendTextButton() {

        wait.until(ExpectedConditions.presenceOfElementLocated(sendTextButton));
        return driver.findElement(sendTextButton).getText();
    }


    public String getTextDisplayOffButton() {

        wait.until(ExpectedConditions.presenceOfElementLocated(displayOffButton));
        return driver.findElement(displayOffButton).getText();
    }


    public String getTextDisplayImageButton() {

        wait.until(ExpectedConditions.presenceOfElementLocated(displayImageButton));
        return driver.findElement(displayImageButton).getText();
    }


    public String getTextDisplayVideoButton() {

        wait.until(ExpectedConditions.presenceOfElementLocated(displayVideoButton));
        return driver.findElement(displayVideoButton).getText();
    }


    /*
    //TODO SL Find Way to get text from Toas message
    public boolean enterFileNameIsDisplayed() {

        if (driver.findElements(enterImageFileName).size() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean downloadingIsDisplayed() {

        String message = (String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('message.downloading').firstChild.textContent");
        System.out.println(message);
        wait.until(ExpectedConditions.presenceOfElementLocated(fileDownloading));
        if (driver.findElements(fileDownloading).size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    */

}
