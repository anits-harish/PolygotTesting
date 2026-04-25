package com.example.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class AutoItDesktopTest {

    @BeforeClass
    public void checkPlatform() {
        String os = System.getProperty("os.name").toLowerCase();
        if (!os.contains("win")) {
            TestLogger.log("Skipping AutoIt Desktop tests. AutoIt is strictly a Windows framework. Current OS: " + os);
            throw new SkipException("AutoIt is only supported on Windows architectures.");
        }
    }

    @Test
    public void testDesktopNotepad() {
        TestLogger.log("Executing Java AutoIt Desktop Automation for Notepad...");
        
        try {
            // Path to the AutoIt executable (assuming it's installed on the Windows CI runner in standard path)
            String autoItExeLoc = "C:\\Program Files (x86)\\AutoIt3\\AutoIt3.exe";
            
            // Resolve script path
            File scriptFile = new File("src/test/resources/scripts/notepad_test.au3");
            String scriptAbsPath = scriptFile.getAbsolutePath();

            if (!new File(autoItExeLoc).exists()) {
                 TestLogger.log("WARNING: AutoIt3.exe not found. Ensure it is installed on Windows runner.");
                 throw new SkipException("AutoIt executable not found at: " + autoItExeLoc);
            }

            // Command string execution to trigger AutoIt
            ProcessBuilder pb = new ProcessBuilder(autoItExeLoc, scriptAbsPath);
            Process process = pb.start();
            process.waitFor();
            
            TestLogger.log("Successfully executed Notepad UI automation via AutoIt script.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to run AutoIt script", e);
        }
    }
}
