package com.example;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GripScenarioTwo {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try

       { driver.get("https://www.gripinvest.in");

        // Retrieve WebElement 'Login' to perform mouse click
        WebElement navLoginButton = driver.findElement(By.xpath(".//div[1]/div/div/div[4]/div/button[1]/span[contains(text(), 'Login')]"));
        navLoginButton.click();
    	System.out.println("Login Button Clicked");

        // Wait for till page loads
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(120));
        w.until(ExpectedConditions.elementToBeClickable(By.id("loginInput")));

        // WebElement loginButton = driver.findElement(By.xpath("//button/span[contains(text(), 'Login')]"));
        List<WebElement> spans = driver.findElements(By.tagName("button"));
        WebElement loginButton = null;
        for (WebElement element : spans) {
            String text = element.getText();
            if(text.equals("Login")) {
                loginButton = element;
                break;
            }
        }

        if(loginButton != null) {
            // Check If login button is disabled
            String beforeValidInput =  loginButton.getAttribute("class");
            boolean isDisabled = beforeValidInput.contains("disabled");

            // check css value
            String cssValues = loginButton.getCssValue("background-color");

            // Get login button
            WebElement loginInput = driver.findElement(By.id("loginInput"));

            // Add Valid Email
            loginInput.sendKeys("moulik@gripinvest.in");

            // Check If login button is disabled
            String afterValidInput =  loginButton.getAttribute("class");
            boolean isDisabledAfterValid = afterValidInput.contains("disabled");

            // check css value
            String afterValidCssValues = loginButton.getCssValue("background-color");

            // Before entering valid input, button should be disabled
            Assert.assertEquals(isDisabled, true);

            // Check Background Color for Disabled button
            Assert.assertEquals(cssValues, "rgba(226, 228, 232, 1)");

            // After entering valid input, button should be enabled
            Assert.assertEquals(isDisabledAfterValid, false);

            // Check Background color for enabled button
            Assert.assertEquals(afterValidCssValues, "rgba(0, 54, 125, 1)");
        }} catch(Exception e) {
            System.out.println(e);
        }
         finally {

            driver.quit();
        }
    }
}
