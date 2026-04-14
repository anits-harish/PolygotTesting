package com.example.utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteSetupListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        String browser = suite.getParameter("browser");
        if (browser != null && !browser.isEmpty()) {
            System.setProperty("browser", browser);
            System.out.println("TestNG Suite Setup: Setting browser system property to " + browser);
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        // Nothing to clean up parameter-wise
    }
}
