package utility;

import java.io.*;
import java.text.SimpleDateFormat;
import baseplatformANDROID.BaseTest;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import java.util.*;
import java.text.DateFormat;
import java.util.logging.Level;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;

import static baseplatformIOS.BaseTest.getIdriver;

public class ReusMeth extends BaseTest {

	public static String timeStampLong() {
		DateFormat df = new SimpleDateFormat("MMMM/d/yyyy-HH.mm.ss.S");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	public static String timeStampShort() {
		return new SimpleDateFormat("MM.dd/HH-mm", Locale.US).format(new Date());

	}

	public static String logPath(String testName) {
		return System.getProperty("user.dir") + "/ProjectLogsShots/" + testName + "/";
	}

	public static void getScreenShotAndroid(String testName, String methodName) throws IOException {
		File src = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File(logPath(testName) + timeStampLong() + "-methodName-" + methodName + "-screenshot.png"));

	}

	public static void getScreenShotIOS(String testName, String methodName) throws IOException {
		File src = ((TakesScreenshot) getIdriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File(logPath(testName) + timeStampLong() + "-methodName-" + methodName + "-screenshot.png"));

	}

	public static void waiting(long seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000L);
	}

	public static void gettingLogsFromSmartPhone(String testName, String methodName) throws FileNotFoundException {

		List<LogEntry> logEntries = driver.manage().logs().get("logcat").filter(Level.ALL);
		File logFile = new File(logPath(testName) + timeStampLong() + "-methodName-" + methodName + "-LOG.txt");

		PrintWriter logFileWriter = new PrintWriter(logFile);

		for (LogEntry log : logEntries) {
			logFileWriter.println(log);
		}

		logFileWriter.flush();

	}

	public static String deviceUdid() throws IOException, InterruptedException {

		String deviceUdid = null;

		Process p;
		p = Runtime.getRuntime().exec(ADB + " devices");
		p.waitFor();
		String line = null;
		List<String> deviceList = new ArrayList<String>();

		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = input.readLine()) != null) {
			if (line.endsWith("device")) {
				deviceList.add(line.split("\\t")[0]);
			}

		}
		for (String device : deviceList) {
			if (device.length() == 16) {
				return device;
			}
			System.out.println(device);

		}
		return deviceUdid;
	}

	public static String accessoryUdid() throws IOException, InterruptedException {

		String accessoryUdid = null;

		Process p;
		p = Runtime.getRuntime().exec(ADB + " devices");
		p.waitFor();
		String line;
		List<String> deviceList = new ArrayList<String>();

		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = input.readLine()) != null) {
			if (line.endsWith("device")) {
				deviceList.add(line.split("\\t")[0]);
			}

		}
		for (String device : deviceList) {
			if (device.length() <= 12) {
				return device;
			}
			System.out.println(device);

		}
		return accessoryUdid;
	}

	// Change Applicaion Activity -> Partner, by Sergey
	public static void changeActivityToPartnerApp() {
		String aapPackage = "displayridepartner.displayridepartnersampleapp";
		String appActivity = "displayridepartner.displayridepartnersampleapp.MainActivity";

		Activity activity = new Activity(aapPackage, appActivity);

		activity.setAppWaitActivity(appActivity);

		activity.setStopApp(false);
		((AndroidDriver) driver).startActivity(activity);
	}

	// Change Applicaion Activity -> DisplayRide, by Sergey
	public static void changeActivityToDisplayRideApp() {
		String appPackage = "displayride.displayrideandroidapp";
		String appActivity = "displayride.displayrideandroidapp.MainActivity";

		Activity activity = new Activity(appPackage, appActivity);

		activity.setAppWaitActivity(appActivity);

		activity.setStopApp(false);
		((AndroidDriver) driver).startActivity(activity);
	}

}
