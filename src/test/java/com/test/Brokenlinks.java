package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Brokenlinks {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");  // Open the target site
       
    }

    @Test
    public void testBrokenLinksAndImages() {
        // 1. Find all links (a tags) and print them
        List<WebElement> links = driver.findElements(By.tagName("a"));
        int brokenLinksCount = 0;

        System.out.println("Total Links Found: " + links.size());
        for (WebElement link : links) {
            String url = link.getAttribute("href");

            if (url != null && !url.isEmpty()) {
                // Print link text and href
                System.out.println("Link Text: " + link.getText() + " - URL: " + url);

                // Check if the link is broken
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("HEAD");
                    connection.connect();
                    int responseCode = connection.getResponseCode();

                    if (responseCode >= 400) {
                        System.out.println("Broken Link Found: " + url + " (Response Code: " + responseCode + ")");
                        brokenLinksCount++;
                    }
                } catch (Exception e) {
                    System.out.println("Error checking link: " + url);
                }
            }
        }

        // Print total broken links count
        System.out.println("Total Broken Links: " + brokenLinksCount);

        // 2. Find all images (img tags) and print them
        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("Total Images Found: " + images.size());

        for (WebElement image : images) {
            String imageUrl = image.getAttribute("src");
            System.out.println("Image URL: " + imageUrl);
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();  // Close the browser after the test
    }
}


