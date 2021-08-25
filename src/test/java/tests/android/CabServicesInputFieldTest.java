package tests.android;

import baseplatform.DataClass;
import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.PopUpMenuCarriersMainViewDR;
import objects.android.SettingsViewDR;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ReusMeth;

import static org.testng.Assert.assertTrue;

public class CabServicesInputFieldTest extends BaseTest {

    private MainViewDR mv;
    private MoreOptionMenuDR mom;
    private SettingsViewDR st;
    private PopUpMenuCarriersMainViewDR popm;

    private int testCount = 1;

    @BeforeClass
    public void setUpLaunching() {

        mv = new MainViewDR(driver);
        mom = new MoreOptionMenuDR(driver);
        st = new SettingsViewDR(driver);
        popm = new PopUpMenuCarriersMainViewDR(driver);

        mv.updateApplicationSignButtonOkTap();//turning off notification of updating if presented

    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() +
                " --- Test Count = " + testCount + "\033[0m");
        testCount++;
    }


    @AfterMethod
    public void onFinishingMethodWithinCLass() {
        driver.launchApp();
    }

    @Test(dataProvider = "carServiceName-1", dataProviderClass = DataClass.class,
            description = "Verify that the \"Add Car Service\" field on main screen accepts case sensitive chars, " +
                    "Verify that the \"Add Car Service\" field on main screen supports multilanguage," +
                    "Verify that \"Add Car Service\" field on main screen accepts digits," +
                    "Verify that \"Add Car Service\" field on main screen accepts special chars," +
                    "Verify that the \"Add Car Service\" field on main screen accepts emoji," +
                    "Verify that the \"Add Car Service\" field on main screen accepts case sensitive chars, digits, special chars, emoji and double byte chars," +
                    "Verify that the \"Add Car Service\" field on main accepts 12 chars the most")
    public void acceptVarietyValuesTest(String riderNameValue) throws InterruptedException{

        String theName = null;

        mv.moreOptionButtonTap();
        mom.settingsButtonTap();

        st.inputCabServiceIntoField(riderNameValue);
        st.continueTap();
        ReusMeth.waiting(5);

        mv.selectCarrierExpandableField();
         assertTrue(popm.verifyCabServiceIsPresentedInPopupMenu(riderNameValue), "Car Service was not added successfully!!!");
        popm.tapOnLicensePlateNumber(riderNameValue);

    }

    @Test(dataProvider = "carServiceName-2", dataProviderClass = DataClass.class,
            description ="Verify that the \"Add Car Service\" field on main screen does not accept 12 or more chars")
    public void acceptNoMorethan12CharsValueTest(String riderNameMoreThan12Value) throws InterruptedException{

        String theName = null;
        String theCuttedName;

        if (riderNameMoreThan12Value.length() > 12) {
            theCuttedName = riderNameMoreThan12Value.substring(0, 12);
        } else {
            theCuttedName = riderNameMoreThan12Value;
        }

        mv.moreOptionButtonTap();
        mom.settingsButtonTap();

        st.inputCabServiceIntoField(theCuttedName);

        st.continueTap();
        ReusMeth.waiting(5);

        mv.selectCarrierExpandableField();

        assertTrue(popm.verifyCabServiceIsPresentedInPopupMenu(theCuttedName), "Car Service was not added successfully!!!");

        popm.tapOnLicensePlateNumber(riderNameMoreThan12Value);

    }




}
