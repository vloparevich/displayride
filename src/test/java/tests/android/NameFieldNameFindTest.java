package tests.android;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertTrue;

import utility.ReusMeth;

public class NameFieldNameFindTest extends BaseTest {

    private MainViewDR mv;


    @BeforeClass
    public void setUpLaunching() {

        String testTestName = this.getClass().getSimpleName();
        System.out.println("\033[0;36m testTestName: " + testTestName +"\033[0m");
        mv = new MainViewDR(driver);

        String accessoryId = getProperty("accessoryId");
    }

    @Parameters({"riderName"})
    @Test
    public void nameIsDisplayedInTextPreviewFrame(String riderNameValue) throws InterruptedException{

        mv.inputNameIntoField(riderNameValue);
        ReusMeth.waiting(20);
        mv.displayNowTap();
        String theName = null;

        ReusMeth.waiting(10);
        theName = String.join(" ", mv.gettingNameFromNameField());


        System.out.println("\033[0;36m riderNameValue: " + riderNameValue +"\033[0m");
        System.out.println("\033[0;36m The Add Name field is filled with: " + String.join("", theName)+"\033[0m");

        assertTrue(theName.equals(riderNameValue), "Test failed");

        if (theName.equals(riderNameValue)) {
            System.out.println("NameFieldNameFind Passed - " + theName);
        } else {
            System.out.println("NameFieldNameFind Failed - " + theName + " -- " + riderNameValue);
        }

     }

    @AfterClass
    public void tearDown() {

    }


}
