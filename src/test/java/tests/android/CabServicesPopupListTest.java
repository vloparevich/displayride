package tests.android;

import baseplatformANDROID.BaseTest;
import io.appium.java_client.android.AndroidElement;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.PopUpMenuCarriersMainViewDR;
import objects.android.SettingsViewDR;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ReusMeth;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class CabServicesPopupListTest extends BaseTest {

	private MainViewDR mv;
	private MoreOptionMenuDR mom;
	private SettingsViewDR st;
	private PopUpMenuCarriersMainViewDR popm;
	private String[] aCarrier = { "InstaRyde", "DiDi", "Uber" };
	private String[] addedCabService = { "Taxi\uD83D\uDE96", "タクシー\uD83D\uDE96" };

	private int testCount = 1;

	@BeforeClass
	public void setUpLaunching() {

		mv = new MainViewDR(driver);
		mom = new MoreOptionMenuDR(driver);
		st = new SettingsViewDR(driver);
		popm = new PopUpMenuCarriersMainViewDR(driver);

		mv.updateApplicationSignButtonOkTap();// turning off notification of updating if presented

	}

	@BeforeMethod
	public void onStartingMethodWithinClass() {
		System.out
				.println("\033[0;95m" + this.getClass().getSimpleName() + " --- Test Count = " + testCount + "\033[0m");
		testCount++;
		driver.launchApp();
	}

	@AfterMethod
	public void onFinishingMethodWithinCLass() {
		// driver.launchApp();
	}

	// Verify that main page shows 1 selected Cab Service
	@Test
	public void oneSelectedCabServiceTest() throws InterruptedException {

		mv.moreOptionButtonTap();
		mom.settingsButtonTap();

		mv.moreOptionButtonTap();
		mom.resetAppButtonTap();

		mom.resetAppButtonYesTap();

		ReusMeth.waiting(5);

		st.tapOnParticularCarrier(aCarrier[0]);

		ReusMeth.waiting(15);

		st.continueTap();

		System.out.println("\033[0;95m A Cab Service: " + aCarrier[0] + "\033[0m");
		System.out.println("\033[0;95m mv.getCabServiceName(): " + mv.getCabServiceName() + "\033[0m");

		assertTrue((mv.getCabServiceName().equals(aCarrier[0])), "Failed. Cab Service Setting up is wrong.");

	}

	// Verify that main page shows 3 selected Cab services
	@Test
	public void threeSelectedCabServiceTest() throws InterruptedException {

		mv.moreOptionButtonTap();
		mom.settingsButtonTap();

		mv.moreOptionButtonTap();
		mom.resetAppButtonTap();

		mom.resetAppButtonYesTap();

		ReusMeth.waiting(5);

		for (int i = 0; i < aCarrier.length; i++) {
			st.tapOnParticularCarrier(aCarrier[i]);
		}

		ReusMeth.waiting(15);
		st.continueTap();

		ReusMeth.waiting(5);
		mv.selectCarrierExpandableField();

		for (int i = 0; i < aCarrier.length; i++) {
			System.out.println("\033[0;95m mv.aCarrier Name: " + aCarrier[i] + "\033[0m");
			assertTrue((popm.verifyCabServiceIsPresentedInPopupMenu(aCarrier[i])),
					"Failed. Cab Service Setting up is wrong.");
		}
	}

	// Verify that main screen pop up list shows typed in Cab service
	@Test
	public void typedInCabServiceIsDisplayedTest() throws InterruptedException {

		mv.moreOptionButtonTap();
		mom.settingsButtonTap();

		mv.moreOptionButtonTap();
		mom.resetAppButtonTap();

		mom.resetAppButtonYesTap();

		ReusMeth.waiting(5);

		for (int i = 0; i < aCarrier.length; i++) {
			st.tapOnParticularCarrier(aCarrier[i]);
		}

		ReusMeth.waiting(5);

		st.inputCabServiceIntoField(addedCabService[0]);

		ReusMeth.waiting(15);
		st.continueTap();

		ReusMeth.waiting(5);
		mv.selectCarrierExpandableField();

		assertTrue((popm.verifyCabServiceIsPresentedInPopupMenu(addedCabService[0])),
				"Failed. Cab Service Setting up is wrong.");

	}

	// Verify that main screen pop up list shows typed in Cab service
	// Also, the added Cab Service can be selected on MainView
	@Test
	public void addedCabServiceCanBeSelectedTest() throws InterruptedException {

		mv.moreOptionButtonTap();
		mom.settingsButtonTap();

		mv.moreOptionButtonTap();
		mom.resetAppButtonTap();

		mom.resetAppButtonYesTap();

		ReusMeth.waiting(5);

		for (int i = 0; i < aCarrier.length; i++) {
			st.tapOnParticularCarrier(aCarrier[i]);
		}

		ReusMeth.waiting(5);

		st.inputCabServiceIntoField(addedCabService[0]);

		ReusMeth.waiting(15);
		st.continueTap();

		ReusMeth.waiting(15);
		mv.selectCarrierExpandableField();

		popm.tapOnParticularCarrier(addedCabService[0]);

		assertTrue(mv.getCabServiceName().equals(addedCabService[0]), "Failed. Car Service Setting up is wrong.");

	}

	// Verify that user can change typed in Cab service
	@Test
	public void typedInCabServiceCanBeChanged() throws InterruptedException {

		for (int j = 0; j < addedCabService.length; j++) {
			mv.moreOptionButtonTap();
			mom.settingsButtonTap();

			mv.moreOptionButtonTap();
			mom.resetAppButtonTap();

			mom.resetAppButtonYesTap();

			ReusMeth.waiting(5);

			for (int i = 0; i < aCarrier.length; i++) {
				st.tapOnParticularCarrier(aCarrier[i]);
			}

			ReusMeth.waiting(5);

			st.inputCabServiceIntoField(addedCabService[j]);
			ReusMeth.waiting(5);
			st.continueTap();
			ReusMeth.waiting(10);
			mv.selectCarrierExpandableField();
			ReusMeth.waiting(5);

			assertTrue((popm.verifyCabServiceIsPresentedInPopupMenu(addedCabService[j])),
					"Failed. Cab Service Setting up is wrong.");

			popm.tapOnParticularCarrier(addedCabService[j]);
			ReusMeth.waiting(3);
		}

	}

	// Verify that main page shows all selected Cab services (all Cab Services are
	// selected)
	// May need to add scroll down function
	@Test
	public void allSelectedCabServiceTest() throws InterruptedException {

		List<AndroidElement> theListST = new ArrayList<AndroidElement>();

		mv.moreOptionButtonTap();
		mom.settingsButtonTap();

		ReusMeth.waiting(5);
		st.allCarriersButtonsTurnOn();

		theListST = st.getListOfAllCheckedCarriersButtonsText();

		String[] theListpopm, theCabList = new String[theListST.size()];

		for (int i = 0; i < theListST.size(); i++) {
			theCabList[i] = theListST.get(i).getText();
		}

		ReusMeth.waiting(5);

		st.continueTap();

		ReusMeth.waiting(5);
		mv.selectCarrierExpandableField();

		ReusMeth.waiting(5);

		theListpopm = popm.getAllListOfCarriersFromPopUpMenu(theCabList);
		ReusMeth.waiting(10);

		System.out.println("\033[0;34m popup size: " + theListpopm.length + "\033[0m");

		boolean onTheList = false;

		for (int i = 0; i < theListpopm.length; i++) {
			for (int j = 0; j < theCabList.length; j++) {
				if (theCabList[j].equals(theListpopm[i])) {
					onTheList = true;
					break;
				}
			}
			assertTrue(onTheList, "Failed. Car Service Setting up is wrong.");
		}
	}

	// Verify that previous selected Cab service on MainView is still selected
	// after selecting more driver services from Driver Setting menu
	@Test
	public void previousSelectedCabServiceTest() throws NullPointerException, InterruptedException {

		String previousCabServiceName = mv.getCabServiceName();

		mv.moreOptionButtonTap();
		mom.settingsButtonTap();

		st.allCarriersButtonsTurnOn();

		ReusMeth.waiting(15);
		st.continueTap();
		ReusMeth.waiting(5);

		System.out.println("\033[0;95m previous selected Car Service: " + previousCabServiceName + "\033[0m");
		System.out.println("\033[0;95m mv.getCabServiceName(): " + mv.getCabServiceName() + "\033[0m");

		assertTrue(previousCabServiceName.equals(mv.getCabServiceName()),
				"Failed. Previous selected Car Service changed!");

	}

	// Verify that 3 selected Cab services are highlighted
	@Test
	public void displaySelectedCabService() throws InterruptedException {
		mv.moreOptionButtonTap();
		mom.settingsButtonTap();

		mv.moreOptionButtonTap();
		mom.resetAppButtonTap();

		ReusMeth.waiting(5);
		mom.resetAppButtonYesTap();

		ReusMeth.waiting(5);

		for (int i = 0; i < aCarrier.length; i++) {
			st.tapOnParticularCarrier(aCarrier[i]);
		}

		for (int i = 0; i < aCarrier.length; i++) {
			assertTrue(st.particularCarrierChecked(aCarrier[i]));
		}
		st.continueTap();
	}

	// Verify that all selected Cab services are highlighted
	@Test
	public void testings() throws InterruptedException {
		List<AndroidElement> theList = new ArrayList<AndroidElement>();

		MainViewDR mv = new MainViewDR(driver);
		SettingsViewDR st = new SettingsViewDR(driver);
		MoreOptionMenuDR mom = new MoreOptionMenuDR(driver);

		mv.moreOptionButtonTap();
		mom.settingsButtonTap();
		ReusMeth.waiting(5);

		st.allCarriersButtonsTurnOn();
		ReusMeth.waiting(5);

		theList = st.getListOfAllCarriersButtonsText();
		ReusMeth.waiting(5);

		for (int i = 0; i < theList.size(); i++) {
			// System.out.println("\033[0;95m In the test, Carrier's Name: " +
			// theList.get(i).getText()+ "\033[0m");
			assertTrue(st.particularCarrierChecked(theList.get(i).getText()));
		}

		st.continueTap();
	}
}
