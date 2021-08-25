package tests.android;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.popupmenus.PopUpMenuOfStopDisplayButtonDR;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class StopDisplayingYesTest extends BaseTest {


    private MainViewDR mv;
    private PopUpMenuOfStopDisplayButtonDR popUp;


    @BeforeClass
    public void setUp() {
        mv = new MainViewDR(driver);
        popUp = new PopUpMenuOfStopDisplayButtonDR(driver);
    }

    @Parameters({"riderName"})
    @Test(invocationCount = 5)
    public void stopDisplayingPopUpMenuTest(String riderName){

        mv.inputNameIntoField(riderName);
        mv.displayNowTap();
        mv.stopDisplayTap();

        //verify all the text in the menu presented

        popUp.stopDisplayYesTap();

    }


}
