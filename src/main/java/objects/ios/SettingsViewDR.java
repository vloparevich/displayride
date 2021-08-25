package objects.ios;

import baseclassIOS.BaseViewSuperClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class SettingsViewDR extends BaseViewSuperClass {

    //Another Screens
    private MainViewDR mv = new MainViewDR(idriver);
    private MoreOptionMenuDR mom = new MoreOptionMenuDR(idriver);

    //UI Carriers Buttons
    private By uberButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Uber']");
    private By lyftButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Lyft']");
    private By olaButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Ola']");
    private By didiButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Didi']");
    private By curbButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Curb']");
    private By gettButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Gett']");
    private By careemButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Careem']");
    private By yandexButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Yandex']");
    private By grabButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Grab']");
    private By InstaRydeButton = By.xpath("//XCUIElementTypeStaticText[@value = 'InstaRyde']");
    private By taxifyButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Taxify']");
    private By cabifyButton = By.xpath("//XCUIElementTypeStaticText[@value = 'Cabify']");
    private By easyTaxiButton = By.xpath("//XCUIElementTypeStaticText[@value = 'EasyTaxi']");
    private By goCatchButton = By.xpath("//XCUIElementTypeStaticText[@value = 'GoCatch']");

    private By resetAppButton = By.xpath("XCUIElementTypeButton[@name = 'resetButton']");

    //All Carriers
    public By allCarriersButtons = By.className("android.widget.CheckBox"); //Locator for all Carriers Buttons, return 15 with Manual Input

    // "Add Cab Service" input field
    private By cabServiceEditField = By.id("//XCUIElementTypeButton[@value = 'Add Cab Service']");
    // Continue Button
    public By continueButton = By.xpath("//XCUIElementTypeButton[@label=\"Continue\"]");
    // "Select all Cab Services you work for" text header
    public By selectAllCabTextHeader = By.xpath("//XCUIElementTypeStaticText[@value = 'Select all Cab Services you work for']");


    public By getUberButton() {
        return uberButton;
    }

    public By getLyftButton() {
        return lyftButton;
    }

    public SettingsViewDR(IOSDriver idriver) {
        super(idriver);
    }

    /*//Turn on All Carriers Buttons
    public void allCarriersButtonsTurnOn() {

        List<IOSElement> allCarriersButton = new ArrayList<IOSElement>();
        allCarriersButton = idriver.findElements(allCarriersButtons);

        for (int i = 0; i < allCarriersButton.size() - 1; i++) { //allCarriersButton.size()-1 Return 15 Elements instead of 14 (Only Cab Services Buttons)
            if (allCarriersButton.get(i).getAttribute("checked").equals("false")) {
                t.tap(allCarriersButton.get(i)).perform();
            }
        }
    }

    //Get List of All Carriers Buttons
    public List<IOSElement> getListOfAllCarriersButtons() {

        wait.until(ExpectedConditions.presenceOfElementLocated(uberButton));

        return idriver.findElements(allCarriersButtons);
    }


    //Turn Off All Carriers Buttons
    public void allCarriersButtonsTurnOff() {

        wait.until(ExpectedConditions.presenceOfElementLocated(uberButton));

        List<IOSElement> allCarriersButton = new ArrayList<IOSElement>();
        allCarriersButton = idriver.findElements(allCarriersButtons);

        for (int i = 0; i < allCarriersButton.size() - 1; i++) { //allCarriersButton.size()-1 Return 15 Elements instead of 14 (Only Cab Services Buttons)
            if (allCarriersButton.get(i).getAttribute("checked").equals("true")) {
                t.tap(allCarriersButton.get(i)).perform();
            }
        }
    }


    //Send Text to the Add Cab Service
    public void inputCabServiceIntoField(String CabService) {

        idriver.findElement(cabServiceEditField).sendKeys(CabService);

        try {
            idriver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Status-OK " + e + "- No Keyboard is presented");
        }
    }*/

    public void continueTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        idriver.findElement(continueButton).click();
    }


    //Check the Status of Floating Widget and Turn Off (if the status if On)
    public void floatingWidgetTurnOff() {

        if (idriver.findElement(selectAllCabTextHeader).getText().equals("Floating Widget ON")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(selectAllCabTextHeader));
            t.tap(idriver.findElement(selectAllCabTextHeader)).perform();
        }
    }


    //Check the Status of Floating Widget and Turn On (if the status if Off)
    public void floatingWidgetTurnOn() {

        if (idriver.findElement(selectAllCabTextHeader).getText().equals("Floating Widget OFF")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(selectAllCabTextHeader));
            t.tap(idriver.findElement(selectAllCabTextHeader)).perform();
        }
    }

    //Send the Carrier name and Turn Off Button
    public void carrierButtonTurnOff(By carrier) {

        if (idriver.findElement(carrier).getAttribute("checked").equals("true")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(carrier));
            t.tap(idriver.findElement(carrier)).perform();
        }
    }


    //Get Cheked Button status
    public String getCheckedStatusUiElement(By locator) {

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return idriver.findElement(locator).getAttribute("checked");
    }


    //Get text from from Floating Widget
    public String getTextFromFW() {

        wait.until(ExpectedConditions.presenceOfElementLocated(selectAllCabTextHeader));
        String strFW = idriver.findElement(selectAllCabTextHeader).getAttribute("text");

        //Cut last word from a string
        return strFW.substring(0, strFW.lastIndexOf(" "));
    }


    //Get text from UI Element
    public String getTextUiElement(By locator) {

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return idriver.findElement(locator).getAttribute("text");
    }

    //Created By Vadim
    public void navigateToMainViewForUberIfneeds() {

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(selectAllCabTextHeader));
            while (idriver.findElements(selectAllCabTextHeader).size() > 0) {
                wait.until(ExpectedConditions.presenceOfElementLocated(uberButton));
                idriver.findElement(uberButton).click();
                continueTap();
            }
        } catch (Exception e) {
            System.out.println("Driver has landed on the needed view");
        }
    }
}
