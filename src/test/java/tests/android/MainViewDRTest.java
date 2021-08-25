package tests.android;

import baseplatform.DataClass;
import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.SettingsViewDR;
import objects.android.popupmenus.PopUpMenuOfStopDisplayButtonDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertTrue;

public class MainViewDRTest extends BaseTest {
	private MainViewDR mv;
	private PopUpMenuOfStopDisplayButtonDR pm;
	private SettingsViewDR setView;
	String testName = null;

	@BeforeClass
	public void setUpLaunching() {
		testName = this.getClass().getSimpleName();
		mv = new MainViewDR(driver);
		pm = new PopUpMenuOfStopDisplayButtonDR(driver);
		setView = new SettingsViewDR(driver);
		setView.navigateToMainViewForUberAndLyftIfneeds();

	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		ReusMeth.waiting(5);
	}

	@BeforeMethod
	public void onStartingMethodWithinClass() {
		mv.updateApplicationSignButtonOkTap();// turning off notification of updating if presented
	}

	@AfterMethod
	public void relaunchApp() {
		driver.launchApp();
	}

	@AfterMethod
	public void onFinishingMethodWithinCLass() {
		driver.closeApp();
	}

	@Test(invocationCount = 10, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
	public void nameIsDisplayedInTextPreviewFrame(String riderNameValue) throws IOException, InterruptedException {
		mv.inputNameIntoField(riderNameValue);
		mv.displayNowTap();
		mv.gettingNameFromNameField();
		mv.getContentFromTextPreviewFrameArray();
		assertTrue(mv.verifyNameIsPresentedInMessageFrame(), "The name is displayed Incorrectly");
	}

	@Test(invocationCount = 5, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
	public void textConsistencyDisplayRide(String riderNameValue) throws IOException, InterruptedException {
		mv.inputNameIntoField(riderNameValue);
		mv.displayNowTap();
		mv.stopDisplayTap();
		assertTrue(pm.displayRideHeaderText().equals("Display Ride"));
	}

	@Test(invocationCount = 1, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
	public void textConsistencyStopDisplay(String riderNameValue) throws IOException, InterruptedException {
		mv.inputNameIntoField(riderNameValue);
		mv.displayNowTap();
		mv.stopDisplayTap();
		assertTrue(pm.stopDisplayText().equals("Stop Display"));
	}

	@Test(invocationCount = 1, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
	public void textConsistencyAreYouSure(String riderNameValue) throws IOException, InterruptedException {
		mv.inputNameIntoField(riderNameValue);
		mv.displayNowTap();
		mv.stopDisplayTap();
		assertTrue(pm.areYouSureText().equals("Are you sure?"));
	}

	@Test(invocationCount = 1, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
	public void textConsistencyTurnDispOff(String riderNameValue) throws IOException, InterruptedException {
		mv.inputNameIntoField(riderNameValue);
		mv.displayNowTap();
		mv.stopDisplayTap();
		pm.stopDisplayYesTap();
		assertTrue(mv.getContentFromTextPreviewFrameString().equals("Turning Display Off, Please Wait"));
	}

	@Test(invocationCount = 1, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
	public void textConsistencyOff(String riderNameValue) throws IOException, InterruptedException {
		mv.inputNameIntoField(riderNameValue);
		mv.displayNowTap();
		mv.stopDisplayTap();
		pm.stopDisplayYesTap();
		ReusMeth.waiting(22);// wait for the second sign for OFF
		assertTrue(mv.getContentFromTextPreviewFrameString().equals("OFF"));
	}
}
