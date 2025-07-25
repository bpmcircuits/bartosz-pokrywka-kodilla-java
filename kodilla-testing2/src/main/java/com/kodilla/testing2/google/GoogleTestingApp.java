package com.kodilla.testing2.google;

import org.openqa.selenium.By;
import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleTestingApp {

    public static final String SEARCHFIELD = "q";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "/Users/bartoszpokrywka/Documents/JAVA/Kodilla/chromedriver");
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get("https://www.google.com");

        driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click();
        WebElement searchField = driver.findElement(By.name(SEARCHFIELD));
        searchField.sendKeys("Feberis Pro");
        searchField.submit();
    }
}
