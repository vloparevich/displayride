package tests.android;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertTrue;

public class DRAppLaunch5Times extends BaseTest {

    private MainViewDR mvDR;

    String testName = null;


    @BeforeClass
    public void setUpLaunching() {

        testName = this.getClass().getSimpleName();
        mvDR = new MainViewDR(driver);
        String accessoryId = getProperty("accessoryId");
        System.out.println(accessoryId);

    }

    @Parameters({"riderName"})
    @Test
    public void nameIsDisplayedInTextPreviewFrame(String riderNameValue) {
        int i = 5;

        while (i <= 0) {
            mvDR.inputNameIntoField(riderNameValue);
            //mvDR.displayNowTap();
            //mvDR.gettingNameFromNameField();
            mvDR.getContentFromTextPreviewFrameArray();
            System.out.println("The rider is" + mvDR.getContentFromTextPreviewFrameArray());
            //assertTrue(mvDR.verifyNameIsPresentedInMessageFrame(), "The name is displayed Incorrectly");
            i--;
        }
    }


    @AfterClass
    public void tearDown() {

    }


}
