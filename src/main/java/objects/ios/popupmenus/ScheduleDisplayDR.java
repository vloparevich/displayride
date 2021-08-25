package objects.ios.popupmenus;

import baseclassIOS.BaseViewSuperClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

public class ScheduleDisplayDR extends BaseViewSuperClass {

    private By schdeuleDisplayTextField = By.xpath("//XCUIElementTypeStaticText[@name='Schedule Display']");
    private By startAfterTxtField = By.xpath("//XCUIElementTypeStaticText[@name='Start after']");
    private By firstWatchLabelTextField = By.xpath("//XCUIElementTypeImage[@name='clockImage'])[1]");
    private By secondWatchLabelTextField = By.xpath("(//XCUIElementTypeImage[@name='clockImage'])[2]");
    private By shouldContinueForTextField  = By.xpath("//XCUIElementTypeStaticText[@name='Display should continue for']");

    private By fiveMinAfterByDefault = By.xpath("//XCUIElementTypeStaticText[@name='05 Mins'])[1]");
    private By fiveMinDurationByDefault = By.xpath("//XCUIElementTypeStaticText[@name='05 Mins'])[2]");
    private By scheduleButton = By.xpath("//XCUIElementTypeButton[@name='Schedule'']");

    public ScheduleDisplayDR(IOSDriver<IOSElement> idriver) {

        super(idriver);
    }


}
