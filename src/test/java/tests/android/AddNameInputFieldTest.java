package tests.android;

import baseplatformANDROID.BaseTest;
import baseplatform.DataClass;
import objects.android.MainViewDR;
import org.testng.annotations.*;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertTrue;

public class AddNameInputFieldTest extends BaseTest {

    private MainViewDR mv;

    private int testCount = 1;

    @BeforeClass
    public void setUpLaunching() {

        mv = new MainViewDR(driver);


    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() +
                " --- Test Count = " + testCount + "\033[0m");
        testCount++;
        //driver.launchApp();
    }

    @AfterMethod
    public void onFinishingMethodWithinCLass() {
        mv.editNameFieldClear();
        //driver.closeApp();
    }

    @Test(dataProvider = "riderName-Provider-3", dataProviderClass = DataClass.class,
            description = "Verify that the \"Add Name\" field on main screen accepts case sensitive chars, " +
                    "Verify that the \"Add Name\" field on main screen supports multilanguage," +
                    "Verify that \"Add Name\" field on main screen accepts digits," +
                    "Verify that \"Add Name\" field on main screen accepts special chars," +
                    "Verify that the \"Add Name\" field on main screen accepts emoji," +
                    "Verify that the \"Add Name\" field on main screen accepts case sensitive chars, digits, special chars, emoji and double byte chars," +
                    "Verify that the \"Add Name\" field on main accepts 64 chars the most")

    public void acceptVarietyValuesTest(String riderNameValue) {

        String theName = null;

        mv.inputNameIntoField(riderNameValue);


        theName = String.join(" ", mv.gettingNameFromNameField());

        assertTrue(theName.equals(riderNameValue), "Test failed");

     /* System.out.println("riderNameValue: " + riderNameValue);
        System.out.println("The Add Name field is filled with " + String.join("", theName));
     */
    }


    @Test(dataProvider = "riderName-Provider-4", dataProviderClass = DataClass.class,
            description ="Verify that the \"Add Name\" field on main screen does not accept 65 or more chars")

    public void acceptMorethan64CharsValueTest(String riderNameMoreThan64CharsValue) {

        String theName = null;
        String theCuttedName;

        if (riderNameMoreThan64CharsValue.length() > 64) {
            theCuttedName = riderNameMoreThan64CharsValue.substring(0, 64);
        } else {
            theCuttedName = riderNameMoreThan64CharsValue;
        }

        mv.inputNameIntoField(riderNameMoreThan64CharsValue);

        theName = String.join(" ", mv.gettingNameFromNameField());

        System.out.println("\033[0;95m riderNameMoreThan64CharsValue: " + riderNameMoreThan64CharsValue + "\033[0m");
        System.out.println("\033[0;95m theCuttedName: " + theCuttedName + "\033[0m");
        System.out.println("\033[0;95m The Add Name field is filled with " + String.join("", theName) + "\033[0m");

        assertTrue(theName.equals(theCuttedName), "Test failed");

    }


}
