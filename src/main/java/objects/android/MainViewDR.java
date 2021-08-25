package objects.android;

import baseclassANDROID.BaseViewSuperClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainViewDR extends BaseViewSuperClass {

	// Elements of main screen of the APP Display Ride

	private By displayNowButton = By.id("btnDisplayNow");
	private By displayLaterButton = By.id("btnDisplayLater");
	private By editNameField = By.id("editName");
	private By carrierExpandButton = By.id("cabName");
	private By messageFrame = By
			.xpath("//android.widget.TextView[@resource-id='displayride.displayrideandroidapp:id/txtPreview']");
	private By snackBarText = By.id("snackbar_text");
	private By displayLaterStatus = By.id("displayLaterStatus");
	private By scheduleButton = By.id("btnSchedule");

	protected By editNameHint = By.xpath("//*[@text = 'Add Name']");
	protected By stopDisplayButtonDR = By.xpath("//android.widget.TextView[@text = 'Stop Display']");
	protected By moreOptionButton = By.xpath("//*[@content-desc = 'More options']");
	protected By updateAppOkButton = By.xpath("//android.widget.Button[@text = 'OK']");
	
	MoreOptionMenuDR mom = new MoreOptionMenuDR(driver);

	public MainViewDR(AndroidDriver<AndroidElement> driver) {
		super(driver);
		
	}

	public MainViewDR(AndroidDriver<AndroidElement> driver, int waitTimeSeconds) {
		super(driver, waitTimeSeconds);
	}

	public void inputNameIntoField(String name) {

		driver.findElement(editNameField).sendKeys(name);

		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			System.out.println("Status-OK " + e + "- No Keyboard is presented");
		}
	}

	public void editNameFieldClear() {
		wait.until(ExpectedConditions.presenceOfElementLocated(editNameField)).clear();

		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			System.out.println("Status-OK " + e + "- No Keyboard is presented");
		}
	}

	// To get the "Add Name" string, by Shuna
	public String getTextOfEditNameString() {
		wait.until(ExpectedConditions.presenceOfElementLocated(editNameHint));
		String editNameText = driver.findElement(editNameHint).getAttribute("text");
		// System.out.println("\033[0;35m !!!!!!!!! editNameText: " + editNameText + "
		// \033[0m");
		return editNameText;
	}

	public void selectCarrierExpandableField() {
		wait.until(ExpectedConditions.presenceOfElementLocated(carrierExpandButton));
		t.tap(driver.findElement(carrierExpandButton)).perform();
	}

	// by Shuna on 06/20/2018
	public String getCabServiceName() {
		wait.until(ExpectedConditions.presenceOfElementLocated(carrierExpandButton));
		System.out.println("Cab Servcec Name" + driver.findElement(carrierExpandButton).getAttribute("text"));
		return driver.findElement(carrierExpandButton).getAttribute("text");
	}

	public void displayNowTap() {

		wait.until(ExpectedConditions.presenceOfElementLocated(displayNowButton));
		t.tap(driver.findElement(displayNowButton)).perform();

	}

	public void stopDisplayTap() {

		wait.until(ExpectedConditions.presenceOfElementLocated(stopDisplayButtonDR));
		t.tap(driver.findElement(stopDisplayButtonDR)).perform();

	}

	public void moreOptionButtonTap() {

		wait.until(ExpectedConditions.presenceOfElementLocated(moreOptionButton));
		t.tap(driver.findElement(moreOptionButton)).perform();

	}

	public void displayLaterTap() {

		t.tap(driver.findElement(displayLaterButton)).perform();
	}

	public String[] gettingNameFromNameField() {

		String[] fullName = driver.findElement(editNameField).getText().trim().split("\\s+");
		return fullName;

	}

	public String[] getContentFromTextPreviewFrameArray() {

		String[] displayedText = driver.findElement(messageFrame).getText().trim().split("\\s+");
		return displayedText;

	}

	public String getContentFromTextPreviewFrameString() {
		wait.until(ExpectedConditions.presenceOfElementLocated(messageFrame));
		String displayedText = driver.findElement(messageFrame).getText();
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

	public void updateApplicationSignButtonOkTap() {

		if (driver.findElements(updateAppOkButton).size() > 0) {
			t.tap(driver.findElement(updateAppOkButton)).perform();
		}

	}

	// By Shuna on 06/04/2018
	public String getSnackBarText() {

		wait.until(ExpectedConditions.presenceOfElementLocated(snackBarText));
		return driver.findElement(snackBarText).getText();
	}

	// By Shuna on 06/06/2018
	public String[] getSnackBarTextArray() {

		wait.until(ExpectedConditions.presenceOfElementLocated(snackBarText));
		return driver.findElement(snackBarText).getText().trim().split(":\\s+");
	}

	public String[] getdisplayLaterStatusTextArray() {

		wait.until(ExpectedConditions.presenceOfElementLocated(displayLaterStatus));
		return driver.findElement(displayLaterStatus).getText().trim().split(":|\\s+");
	}

	public boolean displayLaterStart(long startAfter) throws InterruptedException {

		boolean startAfterBar = false;
		String[] statusText = getdisplayLaterStatusTextArray();
		System.out.println("\033[0;95m " + String.join(" ", statusText) + "\033[0m");

		if (statusText[2].equals("start") && ((startAfter / 60 - 1) == Integer.parseInt(statusText[6]))
				&& (Integer.parseInt(statusText[7]) > 50)) {
			System.out.println("\033[0;95m " + statusText[2] + "\n " + statusText[6] + "\033[0m");
			startAfterBar = true;
		}
		return startAfterBar;
	}

	public boolean displayLaterStop(long duration) throws InterruptedException {

		boolean stopAfterBar = false;
		String[] statusText = getdisplayLaterStatusTextArray();
		System.out.println("\033[0;95m Out of loop String = " + String.join(" ", statusText) + "\033[0m");

		if (statusText[2].equals("stop") && ((duration / 60 - 1) == Integer.parseInt(statusText[6]))
				&& (Integer.parseInt(statusText[7]) > 50)) {
			System.out.println("\033[0;95m inside loop " + statusText[2] + "\n " + statusText[6] + "\033[0m");

			stopAfterBar = true;
		}
		return stopAfterBar;
	}

	public void scheduleButtonTap() {
		t.tap(wait.until(ExpectedConditions.presenceOfElementLocated((scheduleButton)))).perform();
	}

	// Sergey - Navigation from the Main View to the Settings View
	public void navigateToSettingsViewIfNeed() {

		// SL Add Sleep if will need

		try {

			if (driver.findElements(messageFrame).size() > 0) {
				System.out.println("Driver at the Main View");
				moreOptionButtonTap();
				mom.settingsButtonTap();
			}

		} catch (Exception e) {
			System.out.println("Driver not at the Main View or Settings View");
		}
	}

	// Sergey - Wait Until Particula Text Presented in Prewiev Field
	public String waitUntilParticularTextPresented(String randomName) {

		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'" + randomName + "')]")));
		String expectedMessage = driver
				.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + randomName + "')]"))
				.getAttribute("text");
		return expectedMessage;
	}

}
