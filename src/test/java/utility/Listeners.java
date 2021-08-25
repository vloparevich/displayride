package utility;


/*
# Regular Colors
        Black="\[\033[0;30m\]"        # Black
        Red="\[\033[0;31m\]"          # Red
        Green="\[\033[0;32m\]"        # Green
        Yellow="\[\033[0;33m\]"       # Yellow
        Blue="\[\033[0;34m\]"         # Blue
        Purple="\[\033[0;35m\]"       # Purple
        Cyan="\[\033[0;36m\]"         # Cyan
        White="\[\033[0;37m\]"        # White
*/

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static utility.ReusMeth.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Listeners implements ITestListener {


    public void onTestStart(ITestResult iTestResult) {

        /*String testName = this.getClass().getSimpleName();

        try {
            rm.waiting(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Launching test... And not making Screenshot");
        try {

            rm.gettingLogsFromSmartPhone(testName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

    }

    public void onTestSuccess(ITestResult iTestResult) {

        System.out.println("\033[0;32m Test performed successfully!\033[0m");

    }

    public void onTestFailure(ITestResult iTestResult) {

        System.out.println("\033[0;32mTest Failed. Something should be verified! Screenshot is taken on test failure.\033[0m");

        String className2 = iTestResult.getInstanceName();
        String[] classNameShort = className2.split("\\.");
        String newOne = classNameShort[classNameShort.length - 1].toString();
        

        try {
            getScreenShotAndroid(newOne, iTestResult.getName());

        } catch (IOException e) {

            System.out.println("Something went wrong. Screenshot has not been made on test Failure for Android!");
            e.printStackTrace();
        }

       /* try {
            getScreenShotIOS(newOne, iTestResult.getName());

        } catch (IOException e) {
            System.out.println("Something went wrong. Screenshot has not been made on test Failure for iOS!");
            e.printStackTrace();
        }*/

        try {
            gettingLogsFromSmartPhone(newOne, iTestResult.getName());
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong. Logfile has not been made on test Failure!");
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("\033[0;32mListener says that \033[0m"   + iTestResult.getName()+ " was skipped!" );

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("Test suite is being launched...");
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("The TEST is about to finish! Don't sleep! We have a lot of job to do!");
    }
}
