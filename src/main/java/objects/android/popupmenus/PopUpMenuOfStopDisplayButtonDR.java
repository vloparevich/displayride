package objects.android.popupmenus;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import objects.android.MainViewDR;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PopUpMenuOfStopDisplayButtonDR extends MainViewDR {


    private By headerStopDisplayPopMenuTextField = By.xpath("//android.widget.TextView[@text = 'Display Ride']");
    private By stopDisplayPopMenuTextField = By.xpath("//android.widget.TextView[@text = 'Stop Display']");
    private By areYouSurePopMenuTextField = By.xpath("//android.widget.TextView[@text = 'Are you sure?']");
    private By yesButtonPopMenu = By.id("btnYes");
    private By noButtonPopMenu = By.id("btnNo");
    private By headerText = By.xpath("//*[@text = 'Display Ride']");
    private By stopDisplayText = By.xpath("//*[@text = 'Stop Display']");
    private By areYouSureText = By.xpath("//*[@text = 'Are you sure?']");
    private By someButton = By.xpath("//");


    public PopUpMenuOfStopDisplayButtonDR(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void stopDisplayYesTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(yesButtonPopMenu));
        t.tap(driver.findElement(yesButtonPopMenu)).perform();

    }

    public void stopDisplayNoTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(yesButtonPopMenu));
        t.tap(driver.findElement(noButtonPopMenu)).perform();

    }

    public String displayRideHeaderText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(headerText));
        return driver.findElement(headerText).getText();


    }

    public String stopDisplayText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(stopDisplayText));
        return driver.findElement(stopDisplayText).getText();

    }

    public String areYouSureText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(areYouSureText));
        return driver.findElement(areYouSureText).getText();


    }


}
