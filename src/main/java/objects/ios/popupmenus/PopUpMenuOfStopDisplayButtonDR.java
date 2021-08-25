package objects.ios.popupmenus;

import io.appium.java_client.ios.IOSDriver;
import objects.ios.MainViewDR;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PopUpMenuOfStopDisplayButtonDR extends MainViewDR {


    private By headerStopDisplayPopMenuTextField = By.xpath("//XCUIElementTypeStaticText[@value='Stop Display']");

    private By areYouSurePopMenuTextField = By.xpath("//XCUIElementTypeStaticText[@name='Are you sure ?']");
    private By yesButtonPopMenu = By.xpath("//XCUIElementTypeButton[@label='Yes']");
    private By noButtonPopMenu = By.xpath("//XCUIElementTypeButton[@label='No']");


    public PopUpMenuOfStopDisplayButtonDR(IOSDriver idriver) {
        super(idriver);
    }

    public String getTextstopDisplayYesButoon() {

        wait.until(ExpectedConditions.presenceOfElementLocated(yesButtonPopMenu));
         return idriver.findElement(yesButtonPopMenu).getAttribute("name");

    }

    public void stopDisplayYesTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(yesButtonPopMenu));
        t.tap(idriver.findElement(yesButtonPopMenu)).perform();

    }

    public void stopDisplayNoTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(noButtonPopMenu));
        t.tap(idriver.findElement(noButtonPopMenu)).perform();

    }

    public String stopDisplayText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(headerStopDisplayPopMenuTextField));
        return idriver.findElement(headerStopDisplayPopMenuTextField).getAttribute("value");

    }

    public String areYouSureText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(areYouSurePopMenuTextField));
        return idriver.findElement(areYouSurePopMenuTextField).getText();


    }
    public String yesButtonPopMenuText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(yesButtonPopMenu));
        return idriver.findElement(yesButtonPopMenu).getText();

    }

    public String noButtonPopMenuText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(noButtonPopMenu));
        return idriver.findElement(noButtonPopMenu).getText();


    }


}
