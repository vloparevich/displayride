package utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ADBprocessing {


    public static String ADB = System.getenv("ANDROID_HOME") + "/platform-tools/adb";
    private Process process;
    private final String logName;


    public ADBprocessing(String accessoryId, String testName) {

        this.logName = toLogName(accessoryId, testName);
    }

    private String toLogName(String accessoryId, String testName) {
        String name = String.format("adb-%s-%s-i-%d.txt", accessoryId, testName);
        return ReusMeth.logPath(testName);
    }

    public void start(String accessoryId) {

        String cmd = String.format("%s -s %s logcat -v time", ADB, accessoryId);
        //System.out.println("Running adb logcat with cmd= " + cmd);

        try {
            this.process = new ProcessBuilder("/bin/bash", "-c", cmd).redirectOutput(new File(logName)).start();

        } catch (IOException e) {
            System.out.println("!!! Can not run adb logcat because: " + e.getMessage());
        }
    }

    public void stop() {
        if (process == null) {
            System.err.println("!!! Log progress was not found");
            return;
        }

        if (process.isAlive()) {
            process.destroyForcibly();
            try {
                process.waitFor(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.err.println("Process wait terminated with: " + e.getMessage());
             }

            //System.out.println("adb logcat stopped for logName= " + logName);
        }
    }


}
