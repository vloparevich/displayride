package baseclassANDROID;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public abstract class BaseViewSuperClass {

    protected AndroidDriver<AndroidElement> driver;
    protected TouchAction t;
    protected WebDriverWait wait;

    public BaseViewSuperClass(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        t = new TouchAction(driver);
        this.wait = new WebDriverWait(driver, 45);

    }

    public BaseViewSuperClass(AndroidDriver<AndroidElement> driver, int waitTimeSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, waitTimeSeconds);
        t = new TouchAction(driver);

    }
    
    
    public WebElement waitForElementToBeRefreshedAndClickable(By by) {
        return wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(by)));
    }
    
    public WebElement waitForElementToBeRefreshedAndPresent(By by) {
        return wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.presenceOfElementLocated(by)));
    }
}
