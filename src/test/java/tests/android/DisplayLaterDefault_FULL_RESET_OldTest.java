package tests.android;

import baseplatform.DataClass;
import baseplatformANDROID.BaseTest;
import objects.android.MainViewDR;
import objects.android.MoreOptionMenuDR;
import objects.android.SettingsViewDR;
import objects.android.popupmenus.PopUpMenuOfStopDisplayButtonDR;
import objects.android.popupmenus.ScheduleDisplayDR;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;
import utility.ReusMeth;

import java.io.IOException;

import static utility.ReusMeth.*;


public class DisplayLaterDefault_FULL_RESET_OldTest extends BaseTest {

    private SettingsViewDR sv;
    private MainViewDR mv;
    private ScheduleDisplayDR sd;
    private PopUpMenuOfStopDisplayButtonDR stopMenu;
    private SettingsViewDR setView;
    private MoreOptionMenuDR mom;


    @BeforeClass
    public void setUp() {

        mv = new MainViewDR(driver);
        sv = new SettingsViewDR(driver);
        sd = new ScheduleDisplayDR(driver);
        mom = new MoreOptionMenuDR(driver);
        stopMenu = new PopUpMenuOfStopDisplayButtonDR(driver);
        setView = new SettingsViewDR(driver);

    }

    @BeforeMethod
    public void setPreconditions() {

        try {
            sv.carrierButtonTurnOn(sv.uberButton);
            sv.continueTap();
        } catch (Exception e) {
            mv.moreOptionButtonTap();
            mom.settingsButtonTap();
            sv.carrierButtonTurnOn(sv.uberButton);
            sv.continueTap();
        } finally {
            System.out.println("This try catch block will determine if the Device's capabilites are set for FullReset or Not. Still alive. Keep going.");

        }
    }

    @Test(invocationCount = 5, dataProvider = "riderName-Provider-2", dataProviderClass = DataClass.class)
    public void displayLaterDefault(String riderName) throws InterruptedException, IOException {


        waiting(5);

        String accessoryId = accessoryUdid();

        System.out.println("Before ADB processing.....");

        ReusMeth.waiting(5);
        System.out.println("Getting string from frame...");
        String content = mv.getContentFromTextPreviewFrameString();
        // message about connection between the Phone and Accessory
        // mainStatusIterations(previewText, i);
        System.out.println("Verifying string from frame...");
        if (!(content.contains("Turning Display Off") || content.contains("Connected") || content.equals("OFF"))) {

            Runtime.getRuntime().exec(ADB + " -s " + accessoryId + " reboot");  //Accessory-399

            ReusMeth.waiting(90); //wait for Accessory to reboot

        } else {

            String buffer = riderName + " - " + timeStampShort();

            mv.editNameFieldClear();
            mv.inputNameIntoField(buffer);
            mv.displayLaterTap();
            sd.scheduleButtonTap();
            waiting(30);//
            mv.stopDisplayTap();
            stopMenu.stopDisplayYesTap();
            waiting(10);

        }
    }

}


