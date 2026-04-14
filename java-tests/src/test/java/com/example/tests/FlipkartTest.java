package com.example.tests;

import com.example.utils.WebDriverUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlipkartTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        TestLogger.log("Initializing WebDriver for UI Test...");
        driver = WebDriverUtil.getDriver();
    }

    @Test
    public void testLaunchFlipkart() {
        TestLogger.log("Navigating to Flipkart...");
        driver.get("https://www.flipkart.com");

        String pageTitle = driver.getTitle();
        TestLogger.log("Page Title received: " + pageTitle);

        assertTrue(pageTitle.toLowerCase().contains("online"), "Title did not contain 'Online'");
        TestLogger.log("Successfully launched and validated Flipkart website!");

        // Locate the search bar and search for an item
        TestLogger.log("Locating the search bar and searching for 'laptop'...");
        try {
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("laptop");
            searchBox.sendKeys(Keys.ENTER);
            
            // Brief pause to allow the search results page to load before screenshot
            Thread.sleep(3000);
            TestLogger.log("Search query submitted successfully.");
        } catch (Exception e) {
            TestLogger.log("Encountered an issue interacting with the search bar: " + e.getMessage());
        }

        // Take a screenshot and save it
        try {
            TestLogger.log("Taking a screenshot of the web page...");
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "flipkart_launch_" + timestamp + ".png";
            
            Path screenshotsDir = Paths.get("screenshots");
            if (!Files.exists(screenshotsDir)) {
                Files.createDirectories(screenshotsDir);
            }
            
            Path destFile = screenshotsDir.resolve(fileName);
            Files.copy(srcFile.toPath(), destFile, StandardCopyOption.REPLACE_EXISTING);
            TestLogger.log("Screenshot saved successfully at: " + destFile.toAbsolutePath());
        } catch (IOException e) {
            TestLogger.log("Failed to save screenshot: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        TestLogger.log("Closing WebDriver...");
        WebDriverUtil.quitDriver();
    }
}
