package tests.android;

import baseplatformANDROID.BaseTest;
import baseplatform.DataClass;
import objects.android.MainViewDR;
import objects.android.popupmenus.PopUpMenuOfStopDisplayButtonDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertTrue;

public class MainViewDisplayNowTest extends BaseTest {

    private MainViewDR mv;
    private PopUpMenuOfStopDisplayButtonDR pm;

    String testName = null;
    private int testCount = 1;

    @BeforeClass
    public void setUpLaunching() {

        testName = this.getClass().getSimpleName();
        mv = new MainViewDR(driver);
        pm = new PopUpMenuOfStopDisplayButtonDR(driver);

        String accessoryId = getProperty("accessoryId");

        //driver.launchApp();
        mv.updateApplicationSignButtonOkTap();
    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() + " --- Test Count = " + testCount + "\033[0m");
        testCount ++;

        //driver.launchApp();
    }

    @AfterMethod
    public void onFinishingMethodWithinCLass()  throws InterruptedException {
        ReusMeth.waiting(20);
    }

    //Verify that "Add Name" field cannot be empty
    @Parameters
    @Test
    public void noNullValue() throws InterruptedException {

        mv.inputNameIntoField("");
        mv.displayNowTap();

        //System.out.println("\033[0;95m what is this ???  " + mv.getSnackBarText() +  "\033[0m");
        assertTrue(mv.getSnackBarText().equals("Enter Customer Name"), "The text is not right");

        ReusMeth.waiting(10);

    }

    @Test(dataProvider = "riderName-Provider-3", dataProviderClass = DataClass.class,
            description = "Verify that case sensitive chars can be sent to the accessory," +
                    "Verify that Double Byte chars(multilanguages) can be sent to the accessory," +
                    "Verify that digit chars can be sent to the accessory," +
                    "Verify that Special chars can be sent to the accessory," +
                    "Verify that case sensitive chars, digits, special chars, emoji and double byte chars can be sent to the accessory," +
                    "Verify that Emoji chars can be sent to the accessory," +
                    "Verify that only 64 chars can be sent to the accessory")

    public void nameIsDisplayedForRiderWith64Chars(String riderName64CharsValue) throws InterruptedException {
        mv.inputNameIntoField(riderName64CharsValue);
        mv.displayNowTap();

        mv.gettingNameFromNameField();
        mv.getContentFromTextPreviewFrameArray();
        assertTrue(mv.verifyNameIsPresentedInMessageFrame(), "The name is displayed Incorrectly");

        ReusMeth.waiting(30);
        mv.stopDisplayTap();
        ReusMeth.waiting(2);
        pm.stopDisplayYesTap();
    }

    @Test(dataProvider = "riderName-Provider-4", dataProviderClass = DataClass.class,
            description ="Verify that no more than 64 chars can be taken and sent to the accessory")
    public void nameIsDisplayedForRiderWithMoreThan64Chars(String riderNameMoreThan64CharsValue) throws InterruptedException {

        mv.inputNameIntoField(riderNameMoreThan64CharsValue);
        mv.displayNowTap();

        mv.gettingNameFromNameField();
        mv.getContentFromTextPreviewFrameArray();

        String nameFromNameField = String.join("", mv.gettingNameFromNameField());

        assertTrue((mv.verifyNameIsPresentedInMessageFrame() && (nameFromNameField.length() == 64)), "The name is displayed Incorrectly");

        ReusMeth.waiting(25);
        mv.stopDisplayTap();
        ReusMeth.waiting(2);
        pm.stopDisplayYesTap();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        ReusMeth.waiting(20);
    }


}