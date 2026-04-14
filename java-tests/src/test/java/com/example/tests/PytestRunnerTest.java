package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class PytestRunnerTest {

    @Test
    public void executePythonPytestSuite() {
        TestLogger.log("===========================================");
        TestLogger.log(" TestNG Orchestrator: Initializing...");
        TestLogger.log("===========================================");

        try {
            // Path to the python directory
            File pythonTestsDir = new File(System.getProperty("user.dir")).getParentFile();
            File targetDir = new File(pythonTestsDir, "python-tests");

            if (!targetDir.exists()) {
                Assert.fail("python-tests directory not found at " + targetDir.getAbsolutePath());
            }

            // Command to run pytest using the virtual environment
            String venvPython = "venv/bin/python3";
            String[] command = { venvPython, "-m", "pytest", "tests/", "-v" };

            // Note: In Windows it would be venv/Scripts/python, but this is a Mac env based
            // on logs.

            TestLogger.log("TestNG Orchestrator: Triggering Pytest via virtual environment...");

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(targetDir);
            pb.redirectErrorStream(true);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                TestLogger.log("[PYTEST] " + line);
            }

            int exitCode = process.waitFor();
            TestLogger.log("Pytest exited with code: " + exitCode);
            TestLogger.close();
            Assert.assertEquals(exitCode, 0, "Pytest suite failed! Check logs.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to execute Pytest from TestNG: " + e.getMessage());
        }
    }
}
