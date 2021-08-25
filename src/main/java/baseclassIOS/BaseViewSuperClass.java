package baseclassIOS;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class BaseViewSuperClass {

    protected IOSDriver idriver;
    protected TouchAction t;
    protected WebDriverWait wait;


    public BaseViewSuperClass(IOSDriver<IOSElement> idriver) {

        this.idriver = idriver;
        t = new TouchAction(idriver);
        wait = new WebDriverWait(idriver, 45);

    }


    public void tapElement(By iosElement){
        wait.until(ExpectedConditions.presenceOfElementLocated(iosElement));
        t.tap(idriver.findElement(iosElement)).perform();
    }


}
