package core.managers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class ServerManager {

    private static String OS;
    private static String ANDROID_HOME;
    private static String sepparator = null;
    private static String nodeExecutable = null;

    private static String getOS() {
        if (OS == null) OS = System.getProperty("os.name");
        return OS;
    }

    public static boolean isWindows() {
        return getOS().contains("Windows");
    }

    public static boolean isMac() {
        return getOS().contains("Mac");
    }

    public static String getOsSepparator() {
        if (isWindows()) {
            sepparator = "\\";
        }
        if (isMac()) {
            sepparator = "/";
        }
        return sepparator;
    }


    public static String getAndroidHome() {
        if (ANDROID_HOME == null) {
            ANDROID_HOME = System.getenv("ANDROID_HOME");
            if (ANDROID_HOME == null)
                throw new RuntimeException("Failed to find ANDROID_HOME, make sure the environment variable is set");
        }
        return ANDROID_HOME;
    }

    public static String runCommand(String command) {
        String output = null;
        try {
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if (scanner.hasNext()) output = scanner.next();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return output;
    }

    public static String getWorkingDir() {
        return System.getProperty("user.dir");
    }

    public static String read(File file) {
        StringBuilder output = new StringBuilder();
        try {
            String line;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) output.append(line + "\n");
            bufferedReader.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
        return output.toString();
    }

    public static void write(File file, String content) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}