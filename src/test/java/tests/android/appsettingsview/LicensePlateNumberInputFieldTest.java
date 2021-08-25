package tests.android.appsettingsview;

import baseplatform.DataClass;
import baseplatformANDROID.BaseTest;
import objects.android.AppSettingsViewDR;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ReusMeth;

import static org.testng.Assert.assertTrue;

public class LicensePlateNumberInputFieldTest extends BaseTest {

    private MainViewDR mv;
    private MoreOptionMenuDR mom;
    private AppSettingsViewDR ast;

    private int testCount = 0;

    @BeforeClass
    public void setUpLaunching() {

        mv = new MainViewDR(driver);
        mom = new MoreOptionMenuDR(driver);
        ast = new AppSettingsViewDR(driver);

    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
/*        System.out.println("\033[0;95m" + this.getClass().getSimpleName() +
                " --- Test Count = " + testCount + "\033[0m");
 */
          testCount++;
          //driver.launchApp();
    }

    @AfterMethod
    public void onFinishingMethodWithinCLass() {
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

    public void acceptVarietyValuesTest(String riderNameValue) throws InterruptedException{
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() +
                " -----> " + Thread.currentThread().getStackTrace()[1].getMethodName() +
                " --- Test Count = " + testCount + "\033[0m");
        String theName = null;

        System.out.println("\033[0;95m riderNameValue: " + riderNameValue + "\033[0m");

        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();

        ast.inputPlatenumberIntoField(riderNameValue);
        theName = ast.getTextFromPlateNumberFieldString();

        ast.clearPlateNumberField();
        ReusMeth.waiting(10);
        ast.tapContinueButton();


        assertTrue(theName.equals(riderNameValue), "Test failed");

    }


    @Test(dataProvider = "riderName-Provider-4", dataProviderClass = DataClass.class,
            description ="Verify that the \"Add Name\" field on main screen does not accept 65 or more chars")

    public void acceptMorethan64CharsValueTest(String riderNameMoreThan64CharsValue) throws InterruptedException{
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() +
                " -----> " + Thread.currentThread().getStackTrace()[1].getMethodName() +
                " --- Test Count = " + testCount + "\033[0m");

        String theName = null;
        String theCuttedName;

        System.out.println("\033[0;95m riderNameMoreThan64CharsValue: " + riderNameMoreThan64CharsValue + "\033[0m");

        mv.moreOptionButtonTap();
        mom.appSettingsButtonTap();


        if (riderNameMoreThan64CharsValue.length() > 64) {
            theCuttedName = riderNameMoreThan64CharsValue.substring(0, 64);
        } else {
            theCuttedName = riderNameMoreThan64CharsValue;
        }

        ast.inputPlatenumberIntoField(riderNameMoreThan64CharsValue);
        theName = ast.getTextFromPlateNumberFieldString();

        ast.clearPlateNumberField();
        ReusMeth.waiting(10);
        ast.tapContinueButton();

        assertTrue(theName.equals(theCuttedName), "Test failed");

    }


}
