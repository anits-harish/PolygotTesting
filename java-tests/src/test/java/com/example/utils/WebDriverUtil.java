package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.time.Duration;

public class WebDriverUtil {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // Read "browser" system property, default to "chrome"
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            WebDriver webDriver;

            if ("edge".equals(browser)) {
                // Ensure the system uses the local executable instead of downloading it
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/msedgedriver");
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--remote-allow-origins=*");
                webDriver = new EdgeDriver(options);
            } else {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                webDriver = new ChromeDriver(options);
            }
            
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            driver.set(webDriver);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
