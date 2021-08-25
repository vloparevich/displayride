package objects.android;

import baseclassANDROID.BaseViewSuperClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

//By Shuna
public class AppSettingsViewDR extends BaseViewSuperClass {

    //App Settings Radio Buttons and Input Field
    private By dayModeRButton = By.id("dayMode");
    private By nightModeRButton = By.id("nightMode");
    private By autoModeRButton = By.id("auto");
    private By adModeRButton = By.id("ad_mode_switch");
    private By googleAdModeRButton = By.id("googleAd");
    private By partnerModeRButton = By.id("partnerAd");
    private By selfModeRButton = By.id("selfAd");
    private By licenseNumberInputField = By.id("etAddLicence");
    private By continueButton = By.id("btnContinue");

   public AppSettingsViewDR(AndroidDriver < AndroidElement > driver) {
            super(driver);
        }

    private By useNameToLocateElement(String radioButtonName) {

        switch (radioButtonName) {
            case "Day Mode":
                return dayModeRButton;
            case "Night Mode":
                return nightModeRButton;
            case "Auto":
                return autoModeRButton;
            case "Google":
                return googleAdModeRButton;
            case "Partner":
                return partnerModeRButton;
            case "Self":
                return selfModeRButton;
            case "Advertisement Mode":
                return adModeRButton;
            default:
               return null;
        }
    }

    //Check a Radio Button status and Turn it Off if it's On
    public void aRidioButtonTurnOff(String radioButtonName) {
        By theElement = useNameToLocateElement(radioButtonName);

        if (driver.findElement(theElement).getAttribute("checked").equals("true")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(theElement));
            t.tap(driver.findElement(theElement)).perform();
        }
    }

    //Check a Radio Button status and Turn it On if it's Off
    public void aRadioButtonTurnOn(String radioButtonName) {
        By theElement = useNameToLocateElement(radioButtonName);

        if (driver.findElement(theElement).getAttribute("checked").equals("false")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(theElement));
            t.tap(driver.findElement(theElement)).perform();
        }
    }

    //enter text to License Plate Number Input Field
    public void inputPlatenumberIntoField(String name) {
/*        System.out.println("\033[0;36m" + this.getClass().getSimpleName() +
                " -----> " + Thread.currentThread().getStackTrace()[1].getMethodName() +
                " --- String passed in: " + name + "\033[0m");*/

        driver.findElement(licenseNumberInputField).sendKeys(name);

        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Status-OK " + e + "- No Keyboard is presented");
        }
    }

    //clear text from License Plate Number Input Field
    public void clearPlateNumberField() {
        wait.until(ExpectedConditions.presenceOfElementLocated(licenseNumberInputField)).clear();

        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Status-OK " + e + "- No Keyboard is presented");
        }
    }

    //get text from License Plate Number Input Field
    public String getTextFromPlateNumberFieldString() {

        wait.until(ExpectedConditions.presenceOfElementLocated(licenseNumberInputField));
        String displayedText = driver.findElement(licenseNumberInputField).getText();

        return displayedText;
    }

    //Get Checked Radio Button status
    public String getCheckedStatusUIElement(By locator) {

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator).getAttribute("checked");
    }

    public String getCheckedStatusUIElement(String radioButtonName) {
        By theElement = useNameToLocateElement(radioButtonName);

        wait.until(ExpectedConditions.presenceOfElementLocated(theElement));
        return driver.findElement(theElement).getAttribute("checked");
    }

    public void tapContinueButton() {
        t.tap(driver.findElement(continueButton)).perform();
    }

}




