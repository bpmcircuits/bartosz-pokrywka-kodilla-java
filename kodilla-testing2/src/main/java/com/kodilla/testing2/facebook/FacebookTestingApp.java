package com.kodilla.testing2.facebook;

import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FacebookTestingApp {

    private static final String XPATH_WAIT_FOR_COOKIES = "//div[contains(@class, \"_4t2a\")]";
    private static final String XPATH_DEAL_WITH_COOKIES =
            "(//div[contains(@aria-label, \"cookie\")])[2]";
    private static final String XPATH_CREATE_ACCOUNT
            = "//div[contains(@class, \"_6ltg\")]/a[contains(@role, \"button\")]";
    private static final String XPATH_SELECT_DAY = "//select[@name=\"birthday_day\"]";
    private static final String XPATH_SELECT_MONTH = "//select[@name=\"birthday_month\"]";
    private static final String XPATH_SELECT_YEAR = "//select[@name=\"birthday_year\"]";

    private static final String BIRTHDAY_DAY = "7";
    private static final String BIRTHDAY_MONTH = "3";
    private static final String BIRTHDAY_YEAR = "1993";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/bartoszpokrywka/Documents/JAVA/Kodilla/chromedriver");

        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get("https://www.facebook.com/");
        dealWithCookies(driver);
        WebElement createAccount = driver.findElement(By.xpath(XPATH_CREATE_ACCOUNT));
        createAccount.click();
        dealWithCookies(driver);

        selectBirthdayDate(driver, XPATH_SELECT_DAY, BIRTHDAY_DAY);
        selectBirthdayDate(driver, XPATH_SELECT_MONTH, BIRTHDAY_MONTH);
        selectBirthdayDate(driver, XPATH_SELECT_YEAR, BIRTHDAY_YEAR);
    }

    private static void selectBirthdayDate(WebDriver driver, String xpathSelectDate, String birthdayDate) {
        WebElement selectDayCombo = driver.findElement(By.xpath(xpathSelectDate));
        Select selectDay = new Select(selectDayCombo);
        selectDay.selectByValue(birthdayDate);
    }

    private static void dealWithCookies(WebDriver driver) {
        while (!driver.findElement(By.xpath(XPATH_WAIT_FOR_COOKIES)).isDisplayed());
        WebElement acceptCookies = driver.findElement(By.xpath(XPATH_DEAL_WITH_COOKIES));
        acceptCookies.click();
    }
}
