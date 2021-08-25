package tests.android;

import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.popupmenus.ScheduleDisplayDR;
import org.testng.annotations.*;
import utility.ReusMeth;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertTrue;

public class DisplayLaterTest extends BaseTest {

    private MainViewDR mv;
    private ScheduleDisplayDR sd;
    private int testCount = 1;
    private long oneMin = 60;
    private long fiveMins = 300;
    private long tenMins = 600;
    private long fifteenMins = 900;

    String testName = null;

    @BeforeClass
    public void setUp() {

        testName = this.getClass().getSimpleName();
        mv = new MainViewDR(driver);
        sd = new ScheduleDisplayDR(driver);

        String accessoryId = getProperty("accessoryId");

        driver.launchApp();
        mv.updateApplicationSignButtonOkTap();
    }

    @BeforeMethod
    public void onStartingMethodWithinClass() {
        System.out.println("\033[0;95m" + this.getClass().getSimpleName() + " --- Test Count = " + testCount + "\033[0m");
        testCount++;
    }

    @AfterMethod
    public void onFinishingMethodWithinCLass() throws InterruptedException {
        ReusMeth.waiting(5);
    }

    //C340 and C6704: Verify that Display Later can be set start after 1min, duration 1min, also can be tested many times
    @Parameters({"riderName"})
    @Test(invocationCount = 3)
    public void displayLaterOneOneMin(String riderNameValue) throws InterruptedException {

        mv.inputNameIntoField(riderNameValue);

        mv.displayLaterTap();

        sd.selectStartAfterOnScheduleDisplayView();
        sd.selectTimeOneMin();

        sd.selectDurationOnScheduleDisplayView();
        sd.selectTimeOneMin();

        mv.scheduleButtonTap();

        if (mv.displayLaterStart(oneMin)) ReusMeth.waiting(oneMin);
        if (mv.displayLaterStop(oneMin)) ReusMeth.waiting(oneMin);

        assertTrue(mv.getTextOfEditNameString().equals("Add Name"), "Display Later is not successfully stopped");
    }

    //C345: Verify that Display Later can be set start after 10min, duration 5min, also can be tested many times
    @Parameters({"riderName"})
    @Test  //(invocationCount = 5)
    public void displayLaterTenFiveMins(String riderNameValue) throws InterruptedException {

        mv.inputNameIntoField(riderNameValue);

        mv.displayLaterTap();

        sd.selectStartAfterOnScheduleDisplayView();
        sd.selectTimeTenMins();

        sd.selectDurationOnScheduleDisplayView();
        sd.selectTimeFiveMins();

        mv.scheduleButtonTap();

        if (mv.displayLaterStart(tenMins)) ReusMeth.waiting(tenMins);
        if (mv.displayLaterStop(fiveMins)) ReusMeth.waiting(fiveMins);

        assertTrue(mv.getTextOfEditNameString().equals("Add Name"), "Display Later is not successfully stopped");
    }

    //C346: Verify that Display Later can be set start after 5min, duration 5min, also can be tested many times
    @Parameters({"riderName"})
    @Test  //(invocationCount = 5)
    public void displayLaterFiveFiveMins(String riderNameValue) throws InterruptedException {

        mv.inputNameIntoField(riderNameValue);

        mv.displayLaterTap();

        sd.selectStartAfterOnScheduleDisplayView();
        sd.selectTimeFiveMins();

        sd.selectDurationOnScheduleDisplayView();
        sd.selectTimeFiveMins();

        mv.scheduleButtonTap();

        if (mv.displayLaterStart(fiveMins)) ReusMeth.waiting(fiveMins);
        if (mv.displayLaterStop(fiveMins)) ReusMeth.waiting(fiveMins);

        assertTrue(mv.getTextOfEditNameString().equals("Add Name"), "Display Later is not successfully stopped");
    }

    //C347: Verify that Display Later can be set start after 5min, duration 10min, also can be tested many times
    @Parameters({"riderName"})
    @Test  //(invocationCount = 5)
    public void displayLaterFiveTenMins(String riderNameValue) throws InterruptedException {

        mv.inputNameIntoField(riderNameValue);

        mv.displayLaterTap();

        sd.selectStartAfterOnScheduleDisplayView();
        sd.selectTimeFiveMins();

        sd.selectDurationOnScheduleDisplayView();
        sd.selectTimeTenMins();

        mv.scheduleButtonTap();

        if (mv.displayLaterStart(fiveMins))
            ReusMeth.waiting(fiveMins);
        if (mv.displayLaterStop(tenMins))
            ReusMeth.waiting(tenMins);

        assertTrue(mv.getTextOfEditNameString().equals("Add Name"), "Display Later is not successfully stopped");
    }

    //C348 and C350: Verify that Display Later can be set start after 5min, duration 15min, also can be tested many times
    @Parameters({"riderName"})
    @Test  //(invocationCount = 5)
    public void displayLaterFiveFifteenMins(String riderNameValue) throws InterruptedException {

        mv.inputNameIntoField(riderNameValue);

        mv.displayLaterTap();

        sd.selectStartAfterOnScheduleDisplayView();
        sd.selectTimeFiveMins();

        sd.selectDurationOnScheduleDisplayView();
        sd.selectTimeFifteenMins();

        mv.scheduleButtonTap();

        if (mv.displayLaterStart(fiveMins)) ReusMeth.waiting(fiveMins);
        if (mv.displayLaterStop(fifteenMins)) ReusMeth.waiting(fifteenMins);

        assertTrue(mv.getTextOfEditNameString().equals("Add Name"), "Display Later is not successfully stopped");
    }

    //C352: Verify that Display duration can be increased 5mins after 1min delay with 1min duration
    @Parameters({"riderName"})
    @Test  //(invocationCount = 5)
    public void displayLaterOneOnePlusFiveMins(String riderNameValue) throws InterruptedException {

        mv.inputNameIntoField(riderNameValue);

        mv.displayLaterTap();

        sd.selectStartAfterOnScheduleDisplayView();
        sd.selectTimeOneMin();

        sd.selectDurationOnScheduleDisplayView();
        sd.selectTimeOneMin();

        mv.scheduleButtonTap();

        mv.displayNowTap();
        sd.selectTimeFiveMins();

        if (mv.displayLaterStart(oneMin)) ReusMeth.waiting(oneMin);
        if (mv.displayLaterStop(oneMin + fiveMins)) ReusMeth.waiting(oneMin + fiveMins);

        assertTrue(mv.getTextOfEditNameString().equals("Add Name"), "Display Later is not successfully stopped");
    }


    //C353: Verify that Display duration can be increased 10mins after 5min delay with 5min duration
    @Parameters({"riderName"})
    @Test  //(invocationCount = 5)
    public void displayLaterFiveFivePlusTenMins(String riderNameValue) throws InterruptedException {

        mv.inputNameIntoField(riderNameValue);

        mv.displayLaterTap();

        sd.selectStartAfterOnScheduleDisplayView();
        sd.selectTimeFiveMins();

        sd.selectDurationOnScheduleDisplayView();
        sd.selectTimeFiveMins();

        mv.scheduleButtonTap();

        mv.displayNowTap();
        sd.selectTimeTenMins();

        if (mv.displayLaterStart(fiveMins)) ReusMeth.waiting(fiveMins);
        if (mv.displayLaterStop(fiveMins + tenMins)) ReusMeth.waiting(fiveMins + tenMins);

        assertTrue(mv.getTextOfEditNameString().equals("Add Name"), "Display Later is not successfully stopped");
    }

    //C354: Verify that Display duration can be increased 15mins after 5min delay with 5min duration
    @Parameters({"riderName"})
    @Test  //(invocationCount = 5)
    public void displayLaterFiveFivePlusFifteenMins(String riderNameValue) throws InterruptedException {

        mv.inputNameIntoField(riderNameValue);

        mv.displayLaterTap();

        sd.selectStartAfterOnScheduleDisplayView();
        sd.selectTimeFiveMins();

        sd.selectDurationOnScheduleDisplayView();
        sd.selectTimeFiveMins();

        mv.scheduleButtonTap();

        mv.displayNowTap();
        sd.selectTimeFifteenMins();

        if (mv.displayLaterStart(fiveMins)) ReusMeth.waiting(fiveMins);
        if (mv.displayLaterStop(fiveMins + fifteenMins)) ReusMeth.waiting(fiveMins + fifteenMins);

        assertTrue(mv.getTextOfEditNameString().equals("Add Name"), "Display Later is not successfully stopped");
    }

}

