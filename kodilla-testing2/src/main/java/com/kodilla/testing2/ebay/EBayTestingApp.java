package com.kodilla.testing2.ebay;

import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EBayTestingApp {

    public static final String SEARCHFIELD = "_nkw";
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/bartoszpokrywka/Documents/JAVA/Kodilla/chromedriver");
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get("https://www.ebay.com");

        WebElement searchField = driver.findElement(By.name(SEARCHFIELD));
        searchField.sendKeys("laptop");
        searchField.submit();
    }
}
