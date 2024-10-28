package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActionClassTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
       
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        
    }

    @Test
    public void testActionsClass() {
        // Create Actions class instance
        Actions actions = new Actions(driver);

        // WebDriverWait to wait for elements to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Example: Hover over the "Women" menu item
        try {
            // Update the selector to ensure it's correct
            WebElement womenMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Women']")));
            
            // Scroll the element into view if necessary
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", womenMenu);
            
            // Perform hover action
            actions.moveToElement(womenMenu).perform();
            System.out.println("Hovered over the 'Women' menu item.");
            
        } catch (Exception e) {
            System.out.println("Failed to locate the 'Women' menu item: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();  // Close the browser after the test completes
    }
}




