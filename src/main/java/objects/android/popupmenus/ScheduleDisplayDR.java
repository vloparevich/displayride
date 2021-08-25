package objects.android.popupmenus;

import baseclassANDROID.BaseViewSuperClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;


public class ScheduleDisplayDR extends BaseViewSuperClass {

    private By scheduleStartTime = By.id("txtStartTime");
    private By scheduleDuration = By.id("txtDuration");

    private By timeOneMin = By.id("txtOne");
    private By timeTwoMins = By.id("txtTwo");
    private By timeFiveMins = By.id("txtName");
    private By timeTenMins = By.id("txtTen");
    private By timeFifteenMins = By.id("txtFifteen");

    private By scheduleButton = By.id("btnSchedule");

    //protected By editNameHint = By.xpath("//*[@text = 'Add Name']");

    public ScheduleDisplayDR(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void selectStartAfterOnScheduleDisplayView() {
        t.tap(driver.findElement(scheduleStartTime)).perform();
    }

    public void selectDurationOnScheduleDisplayView() {
        t.tap(driver.findElement(scheduleDuration)).perform();
    }

    // These times are for start times AND durations
    // By Shuna
    public void selectTimeOneMin() {
        t.tap(driver.findElement(timeOneMin)).perform();
    }

    public void selectTimeTwoMins() {
        t.tap(driver.findElement(timeTwoMins)).perform();
    }

    public void selectTimeFiveMins() {
        t.tap(driver.findElement(timeFiveMins)).perform();
    }

    public void selectTimeTenMins() {
        t.tap(driver.findElement(timeTenMins)).perform();
    }

    public void selectTimeFifteenMins() {
        t.tap(driver.findElement(timeFifteenMins)).perform();
    }

    public void scheduleButtonTap() {

        t.tap(driver.findElement(scheduleButton)).perform();
    }
}
