package objects.android;

import baseclassANDROID.BaseViewSuperClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
/*
by Sergey
*/
public class SettingsViewDR extends BaseViewSuperClass {

    //Enother Screens
    private MainViewDR mv = new MainViewDR(driver);
    private MoreOptionMenuDR mom = new MoreOptionMenuDR(driver);

    //UI Carriers Buttons
    public By uberButton = By.id("checkUber");
    public By lyftButton = By.id("checkLyft");
    private By olaButton = By.id("checkOla");
    private By didiButton = By.id("checkDidi");
    private By curbButton = By.id("checkCurb");
    private By gettButton = By.id("checkGett");
    private By careemButton = By.id("checkCareem");
    private By yandexButton = By.id("checkYandex");
    private By grabButton = By.id("checkGrab");
    private By blacklaneButton = By.id("checkBlacklane");
    private By taxifyButton = By.id("checkTaxify");
    private By cabifyButton = By.id("checkCabify");
    private By easyTaxiButton = By.id("checkEasyTaxi");
    private By goCatchButton = By.id("checkGoCatch");

    private By resetAppButton = By.xpath("//android.widget.TextView[@resource-id = 'displayride.displayrideandroidapp:id/title' and @text = 'Settings']");

    //UI Carriers Text from Buttons
    public By lyftButtonText = By.xpath("//*[@text='Lyft']");
    public By uberButtonText = By.xpath("//*[@text='Uber']");

    //All Carriers
    public By allCarriersButtons = By.className("android.widget.CheckBox"); //Locator for all Carriers Buttons, return 15 with Manual Input
    private By allCarriersButtonsText = By.xpath("//android.widget.TextView");

    // Input Cab Service
    private By cabServiceEditField = By.id("editOtherCabName");
    // Continue Button
    public By continueButton = By.id("btnContinue");
    // Floating Widget Switch
    public By floatingWidgetSwitch = By.id("floating_widget_switch");

    //Notification
    public By notification = By.id("snackbar_text");


    public SettingsViewDR(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    //Turn on All Carriers Buttons
    public void allCarriersButtonsTurnOn() {

        List<AndroidElement> allCarriersButton = new ArrayList<AndroidElement>();
        allCarriersButton = driver.findElements(allCarriersButtons);

        for (int i = 0; i < allCarriersButton.size() - 1; i++) { //allCarriersButton.size()-1 Return 15 Elements instead of 14 (Only Cab Services Buttons)
            if (allCarriersButton.get(i).getAttribute("checked").equals("false")) {
                t.tap(allCarriersButton.get(i)).perform();
            }
        }
    }


    //Get List of All Carriers Buttons
    public List<AndroidElement> getListOfAllCarriersButtons() {

        wait.until(ExpectedConditions.presenceOfElementLocated(uberButton));
        return driver.findElements(allCarriersButtons);
    }

    //Turn Off All Carriers Buttons
    public void allCarriersButtonsTurnOff() {

        wait.until(ExpectedConditions.presenceOfElementLocated(uberButton));

        List<AndroidElement> allCarriersButton = new ArrayList<AndroidElement>();
        allCarriersButton = driver.findElements(allCarriersButtons);

        for (int i = 0; i < allCarriersButton.size() - 1; i++) { //allCarriersButton.size()-1 Return 15 Elements instead of 14 (Only Cab Services Buttons)
            if (allCarriersButton.get(i).getAttribute("checked").equals("true")) {
                t.tap(allCarriersButton.get(i)).perform();
            }
        }
    }


    //Send Text to the Add Cab Service
    public void inputCabServiceIntoField(String CabService) {

        driver.findElement(cabServiceEditField).sendKeys(CabService);

        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Status-OK " + e + "- No Keyboard is presented");
        }
    }


    public void continueTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        t.tap(driver.findElement(continueButton)).perform();
    }


    //Check the Status of Floating Widget and Turn Off (if the status if On)
    public void floatingWidgetTurnOff() {

        if (driver.findElement(floatingWidgetSwitch).getText().equals("Floating Widget ON")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(floatingWidgetSwitch));
            t.tap(driver.findElement(floatingWidgetSwitch)).perform();
        }
    }


    //Check the Status of Floating Widget and Turn On (if the status if Off)
    public void floatingWidgetTurnOn() {

        if (driver.findElement(floatingWidgetSwitch).getText().equals("Floating Widget OFF")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(floatingWidgetSwitch));
            t.tap(driver.findElement(floatingWidgetSwitch)).perform();
        }
    }


    //Send the Carrier name and Turn On Button
    public void carrierButtonTurnOn(By carrier) {

        if (driver.findElement(carrier).getAttribute("checked").equals("false")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(carrier));
            t.tap(driver.findElement(carrier)).perform();
        }
    }


    //Send the Carrier name and Turn Off Button
    public void carrierButtonTurnOff(By carrier) {

        if (driver.findElement(carrier).getAttribute("checked").equals("true")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(carrier));
            t.tap(driver.findElement(carrier)).perform();
        }
    }


    //Get Cheked Button status
    public String getCheckedStatusUiElement(By locator) {

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator).getAttribute("checked");
    }


    //Get text from from Floating Widget
    public String getTextFromFW() {

        wait.until(ExpectedConditions.presenceOfElementLocated(floatingWidgetSwitch));
        String strFW = driver.findElement(floatingWidgetSwitch).getAttribute("text");

        //Cut last word from a string
        return strFW.substring(0, strFW.lastIndexOf(" "));
    }


    //Get text from UI Element
    public String getTextUiElement(By locator) {

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator).getAttribute("text");
    }

    //Created By Vadim
    public void navigateToMainViewForUberAndLyftIfneeds() {

        try {
            if (driver.findElements(floatingWidgetSwitch).size() > 0) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkUber")));
                carrierButtonTurnOn(uberButton);

                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkLyft")));
                carrierButtonTurnOn(lyftButton);

                continueTap();

            }

        } catch (Exception e) {
            System.out.println("Driver has landed on the needed view");
        }

    }


    //Get List of All Carriers Buttons
    public List<AndroidElement> getListOfAllCarriersButtonsText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(uberButton));


        List<AndroidElement> carriers = driver.findElements(allCarriersButtonsText);

        for (int i = 0; i<carriers.size(); i++) {
            if ( carriers.get(i).getText().equals("Continue")  || carriers.get(i).getText().equals("Select all Cab services you work for") ) {
                carriers.remove(i);
            }
        }
       return carriers;
    }

    //Get List of All Checked Carriers Buttons, by Shuna on 07/10/2018
    public List<AndroidElement> getListOfAllCheckedCarriersButtonsText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(uberButton));

        List<AndroidElement> carriers = getListOfAllCarriersButtonsText();
        List<AndroidElement> carriersChecked = new ArrayList<AndroidElement>();

        for (int i = 0; i<carriers.size(); i++) {
            String byIdString = "check"+carriers.get(i).getText();

            String checkedOrNot = driver.findElement(By.id(byIdString)).getAttribute("checked");
            if (checkedOrNot.equals("true")) {
                carriersChecked.add(carriers.get(i));
            }
        }
        return carriersChecked;
    }


    //Tap on the Particular Carrier, by Sergey
    public void tapOnParticularCarrier(String carrierName) {

        List<AndroidElement> carriersBtn = getListOfAllCarriersButtonsText();

        for (int i = 0; i < carriersBtn.size() - 1; i++) {

            if (carriersBtn.get(i).getAttribute("text").equals(carrierName)) {
                t.tap(carriersBtn.get(i)).perform();
                break;
            }
        }
    }



    //Verify if a Cab Service button is checked, by Shuna on 06/22/2018
    public boolean particularCarrierChecked(String carrierName) {
        boolean ifChecked = false;

        String theCarrierID = "check" + carrierName;
        By theCarrierElement = By.id(theCarrierID);

        if (driver.findElement(theCarrierElement).getAttribute("checked").equals("true")){
            ifChecked = true;
            System.out.println("\033[0;95m carrierName: " + carrierName + "\033[0m");
        }
        return ifChecked;
    }

}




