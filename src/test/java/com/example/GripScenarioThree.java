package com.example;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GripScenarioThree {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.gripinvest.in");

            Actions actions = new Actions(driver);

            // Go to Testimonials WebElement 'Learn' to perform mouse hover
            WebElement testimonialSection = driver.findElement(By.xpath("//div[2]/div[6]/div[3]"));
            // Go to testimonials section
            actions.scrollToElement(testimonialSection).perform();
            System.out.println("Testimonial Section Arrived");

            // Get all testimonial elements in slider
            List<WebElement> allSwiperSlideElemts = testimonialSection.findElements(By.className("swiper-slide"));

            // Get all clickable cards
            List<String> testimonialLinks = new ArrayList<>();
            for(WebElement swiperSlidElement: allSwiperSlideElemts) {
                List<WebElement> totalCards = swiperSlidElement.findElements(By.tagName("a"));
                for(WebElement card : totalCards) {
                    String url = card.getAttribute("href");
                    if(!testimonialLinks.contains(url)) {
                        testimonialLinks.add(url);
                    }
                }
            }

            Assert.assertEquals(testimonialLinks.size(), 10);

    } catch(Exception e) {
        System.out.println(e);
    } finally {
        driver.quit();
    }
    }
}

