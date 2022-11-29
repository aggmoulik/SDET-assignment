package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GripScenarioOne {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.gripinvest.in");

            Actions actions = new Actions(driver);

            //Retrieve WebElement 'Learn' to perform mouse hover
            WebElement menuOption = driver.findElement(By.xpath(".//a[contains(text(), 'Learn')]"));
            //Mouse hover menuOption 'Learn'
            actions.moveToElement(menuOption).perform();
            System.out.println("Done Mouse hover on 'Learn' from Menu");

            WebElement naviagtionSubMenu = driver.findElement(By.xpath(".//div[1]/div/div/div[3]/ul/li[2]/div[2]"));
            List<WebElement> elements = naviagtionSubMenu.findElements(By.tagName("a"));
            List<String> validElements = new ArrayList<>(Arrays.asList("Products", "About Us", "FAQ", "Transparency" , "Blogs" ));
            List<String> elementsFound = new ArrayList<String>();
            for (WebElement element : elements) {
                String text = element.getText();
                elementsFound.add(text);
            }
            Assert.assertEquals(elementsFound, validElements);
            driver.quit();
        } catch(Exception e) {
            System.out.println(e);
            driver.quit();
        }
    }
}
