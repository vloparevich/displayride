package objects.android;

import baseclassANDROID.BaseViewSuperClass;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.MobileElement;
import org.aspectj.apache.bcel.classfile.annotation.ElementValue;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.ArrayList;

public class PopUpMenuCarriersMainViewDR extends BaseViewSuperClass {

    private By allCarriersButtons = By.id("txtName");
    private By allSelectedCabServices = By.id("lytCabList");

    //Constractor
    public PopUpMenuCarriersMainViewDR(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }


    //Get List of Elements
    public List<AndroidElement> getListOfCarriersFromPopUpMenu() {

        wait.until(ExpectedConditions.presenceOfElementLocated(allCarriersButtons));
        return driver.findElements(allCarriersButtons);
    }

    //Get List of Elements, even hidden Elements (need to scroll down)
    public String[] getAllListOfCarriersFromPopUpMenu(String[] cabServicesList) throws InterruptedException {
        String[] allCarriersList = new String[cabServicesList.length];
        List<AndroidElement> allCarriersButtonsList = new ArrayList<AndroidElement>();

        for (int i=0; i<cabServicesList.length; i++) {

           String byString = "//android.widget.TextView[@resource-id = 'displayride.displayrideandroidapp:id/txtName' and @text = '" + cabServicesList[i]  +"']";
//            System.out.println("\033[0;36m carriersBotton List: " + cabServicesList[i] + "; "+ byString + "\033[0m");

            By cabService = By.xpath(byString);
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + cabServicesList[i] + "\"));");
            wait.until(ExpectedConditions.presenceOfElementLocated(cabService));
            allCarriersButtonsList.add(driver.findElement(cabService));
            allCarriersList[i] = allCarriersButtonsList.get(i).getText();
 //           System.out.println("\033[0;34m carriersBotton List: " + allCarriersList[i] + "\033[0m");

        }
        return allCarriersList;
    }


    //By Shuna on 06/19/2018
    public boolean verifyCabServiceIsPresentedInPopupMenu(String aCabService) {
        boolean inPopupMenu = false;

        wait.until(ExpectedConditions.presenceOfElementLocated(allSelectedCabServices));

        List<AndroidElement> allElements = getListOfCarriersFromPopUpMenu();

        for(int i=0; i<allElements.size(); i++){
            System.out.println("\033[0;36m carriersBotton List(in Popm): " + allElements.get(i).getText() + "\033[0m");
            if (allElements.get(i).getText().equals(aCabService))  {
                inPopupMenu = true;
                break;
            }
        }
        System.out.println("\033[0;34m inPopupMenu?  " + inPopupMenu + "\033[0m");
        return inPopupMenu;
    }

    //Tap on the Particular Carrier, by Shuna on 07/05/2018
    public void tapOnLicensePlateNumber(String carrierName) {
         String byString = "//android.widget.TextView[@resource-id = 'displayride.displayrideandroidapp:id/txtName' and @text = '" + carrierName +"']";

         By cabService = By.xpath(byString);

            System.out.println("\033[0;36m carriersBotton List: " + carrierName + "\n"+ byString+"\033[0m");

            wait.until(ExpectedConditions.presenceOfElementLocated(cabService));
            if (driver.findElement(cabService).getAttribute("text").equals(carrierName)) {
                t.tap(driver.findElement(cabService)).perform();
        }
    }

    //Tap on the Particular Carrier, by Serger
    public void tapOnParticularCarrier(String carrierName) {
        System.out.println("\033[0;36m passed in carrierName" + carrierName + "\033[0m");
        List<AndroidElement> carriersBtn = getListOfCarriersFromPopUpMenu();

        for (int i = 0; i < carriersBtn.size(); i++) {
            System.out.println("\033[0;36m carriersBotton List" + carriersBtn.get(i).getAttribute("text")+ "\033[0m");

            if (carriersBtn.get(i).getAttribute("text").equals(carrierName)) {
                t.tap(carriersBtn.get(i)).perform();
                break;
            }
        }
    }

}


