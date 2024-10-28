package com.test;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

public class ExtentedreportUtilities {

    WebDriver driver;
    ExtentTest test;

    @BeforeMethod
    public void setup(Method method) {
        // Initialize WebDriver (ChromeDriver in this example)
       // Set your path
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Create a new test in the Extent Report
        test = ExtentReportManager.createTest(method.getName());
        test.info("Test setup initialized");
    }

    @Test
    public void testExample() {
        // Log test steps
        test.info("Navigating to Automation Exercise website");
        
        // Open the target website
        driver.get("https://automationexercise.com");

        // Capture and log the title of the page
        String title = driver.getTitle();
        test.info("Page title captured: " + title);

        // Assertion to check the title contains 'Automation'
        Assert.assertTrue(title.contains("Automation"), "Title does not contain 'Automation'");
        test.pass("Title verification passed!");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Check the test result and log it accordingly
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        }

        // Quit the driver instance

        // Flush the ExtentReports to save the report
        ExtentReportManager.flushReports();
    
        if (driver != null) {
            driver.quit();
        }
    }
}
