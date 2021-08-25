package objects.ios;

import baseclassIOS.BaseViewSuperClass;

import io.appium.java_client.ios.IOSDriver;
//import objects.ios.MoreOptionMenuDR;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainViewDR extends BaseViewSuperClass {

//Elements of main screen of the APP Display Ride


    private By displayNowButton = By.id("displayNowButton");//+
    private By displayLaterButton = By.id("displayLaterButton");//+
    private By editNameField = By.xpath("//XCUIElementTypeTextField[@value='Add Name']");//+
    // private By editNameField = By.id("EnterRiderNameTextField");//+
    private By carrierExpandButton = By.xpath("//XCUIElementTypeStaticText[@name=\"carServiceNameLabel\"]");//+
    // private By editNameField = By.id("EnterRiderNameTextField");
    private By messageFrame = By.xpath("//XCUIElementTypeStaticText[@name=\"statusLabel\"]");
    //private By snackBarText = By.id("snackbar_text");//notification about updating accessory
    protected By editNameHint = By.xpath("//*[@value = 'Add Name']");
    protected By stopDisplayButtonDR = By.xpath("//XCUIElementTypeButton[@name='displayNowButton']");
    protected By moreOptionButton = By.id("menuButton");
    //protected By updateAppOkButton = By.xpath("//android.widget.Button[@text = 'OK']");//related to snack bar button

    //MoreOptionMenuDR mom = new MoreOptionMenuDR(idriver);

    public MainViewDR(IOSDriver idriver) {

        super(idriver);
    }


    public void inputNameIntoField(String name) {

        idriver.findElement(editNameField).sendKeys(name);

        try {
            idriver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Status-OK " + e + "- No Keyboard is presented");
        }
    }

    public void editNameFieldClear() {
        wait.until(ExpectedConditions.presenceOfElementLocated(editNameField)).clear();

        try {
            idriver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Status-OK " + e + "- No Keyboard is presented");
        }
    }

    public String getTextOfEditNameString() {
        wait.until(ExpectedConditions.presenceOfElementLocated(editNameHint));
        String editNameText = idriver.findElement(editNameHint).getAttribute("text");
        //System.out.println("\033[0;35m  !!!!!!!!!   editNameText: " + editNameText + " \033[0m");
        return editNameText;
    }

    public void selectCarrierExpandableField() {

        t.tap(idriver.findElement(carrierExpandButton)).perform();
    }

    public void displayNowTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(displayNowButton));
        t.tap(idriver.findElement(displayNowButton)).perform();

    }

    public void stopDisplayTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(stopDisplayButtonDR));
        t.tap(idriver.findElement(stopDisplayButtonDR)).perform();

    }


    public void moreOptionButtonTap() {

        wait.until(ExpectedConditions.presenceOfElementLocated(moreOptionButton));
        t.tap(idriver.findElement(moreOptionButton)).perform();

    }


    public void displayLaterTap() {

        t.tap(idriver.findElement(displayLaterButton)).perform();
    }

    public String[] gettingNameFromNameField() {

        String[] fullName = idriver.findElement(editNameField).getText().trim().split("\\s+");
        return fullName;

    }

    public String[] getContentFromTextPreviewFrameArray() {

        String[] displayedText = idriver.findElement(messageFrame).getText().trim().split("\\s+");
        return displayedText;

    }

    public String getContentFromTextPreviewFrameString() {
        wait.until(ExpectedConditions.presenceOfElementLocated(messageFrame));
        String displayedText = idriver.findElement(messageFrame).getText();
        return displayedText;

    }

    public boolean verifyNameIsPresentedInMessageFrame() {

        String[] enteredName = gettingNameFromNameField();
        String[] textInFrame = getContentFromTextPreviewFrameArray();

        int nameEnteredLength = enteredName.length;
        int textInMessageFrameLength = textInFrame.length;

        StringBuilder myNameB = new StringBuilder();
        StringBuilder displayedNameB = new StringBuilder();
        //
        for (int i = textInMessageFrameLength - nameEnteredLength; i < textInMessageFrameLength; i++) {

            displayedNameB.append(textInFrame[i]);

            if (i != textInMessageFrameLength - 1) {
                displayedNameB.append(" ");
            }
        }
        for (int i = 0; i < nameEnteredLength; i++) {

            myNameB.append(enteredName[i]);

            if (i != nameEnteredLength - 1) {
                myNameB.append(" ");
            }
        }

        String myNameS = myNameB.toString();

        String displayedNameS = displayedNameB.toString();

        if (myNameS.equals(displayedNameS)) {
            return true;
        } else {
            return false;
        }

    }

   /* public void updateApplicationSignButtonOkTap() {

        if (idriver.findElements(updateAppOkButton).size() > 0) {
            t.tap(idriver.findElement(updateAppOkButton)).perform();
        }

    }
*/
    /*public String getSnackBarText() {

        wait.until(ExpectedConditions.presenceOfElementLocated(snackBarText));
        String displayedSnackBarText = idriver.findElement(snackBarText).getText();

        return displayedSnackBarText;

    }*/


    //Sergey - Navigation from the Main View to the Settings View
    public void navigateToSettingsViewIfNeed() {

        //SL Add Sleep if will need

        try {

            idriver.findElement(editNameField);
            System.out.println("Driver at the Main View");
            moreOptionButtonTap();
            //mom.settingsButtonTap();

        } catch (Exception e) {
            System.out.println("Driver not at the Main View or Settings View");
        }
    }

}
