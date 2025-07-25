package com.kodilla.testing2.crudapp;

import com.kodilla.testing2.config.WebDriverConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CrudAppTestSuite {

    private static final String BASE_URL = "https://bpmcircuits.github.io";
    private WebDriver driver;
    private Random generator;

    private static boolean ACCEPT_ALERT = true;
    private static boolean REJECT_ALERT = false;

    @BeforeEach
    public void initTests() {
        driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get(BASE_URL);
        generator = new Random();
    }

    @AfterEach
    public void cleanUpAfterTest() {
        driver.close();
    }

    @Test
    public void shouldCreateTrelloCard() throws InterruptedException {
        String taskName = crateCrudAppTestTask();
        sendTestTaskToTrello(taskName);
        assertTrue(checkTaskExistsInTrello(taskName));
        deleteTask(taskName);
    }

    private void deleteTask(String taskName) throws InterruptedException {
        performActionOnTask(taskName,
                form -> form.findElement(
                        By.xpath(".//button[@data-task-delete-button]"))
                        .click(),
                REJECT_ALERT);
    }

    private void sendTestTaskToTrello(String taskName) throws InterruptedException {
        performActionOnTask(taskName,
                form -> form.findElement(
                        By.xpath(".//button[contains(@class, \"card-creation\")]"))
                        .click(),
                ACCEPT_ALERT);
    }

    private void performActionOnTask(String taskName, Consumer<WebElement> actionAfterSelect, boolean acceptAlert)
            throws InterruptedException {

        driver.navigate().refresh();

        while (!driver.findElement(By.xpath("//select[1]")).isDisplayed());

        driver.findElements(By.xpath("//form[@class=\"datatable__row\"]")).stream()
                .filter(form ->
                        form.findElement(By.xpath(".//p[@class=\"datatable__field-value\"]"))
                                .getText().equals(taskName))
                .forEach(form -> {
                    WebElement selectElement = form.findElement(By.xpath(".//select[1]"));
                    Select select = new Select(selectElement);
                    select.selectByIndex(1);

                    actionAfterSelect.accept(form);
                });

        Thread.sleep(5000);

        if (acceptAlert) {
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (NoAlertPresentException e) {
                System.out.println("No alert present");
            }
        }
    }

    private String crateCrudAppTestTask() throws InterruptedException {
        final String XPATH_TASK_NAME = "//form[contains(@method, 'POST')]/fieldset[1]/input";
        final String XPATH_TASK_CONTENT = "//form[contains(@method, 'POST')]/fieldset[2]/textarea";
        final String XPATH_ADD_BUTTON = "//form[contains(@method, 'POST')]/fieldset[3]/button";

        String taskName = "Task number " + generator.nextInt(100000);
        String taskContent = taskName + " content";

        WebElement name = driver.findElement(By.xpath(XPATH_TASK_NAME));
        name.sendKeys(taskName);

        WebElement content = driver.findElement(By.xpath(XPATH_TASK_CONTENT));
        content.sendKeys(taskContent);

        WebElement addButton = driver.findElement(By.xpath(XPATH_ADD_BUTTON));
        addButton.click();
        Thread.sleep(2000);

        return taskName;
    }

    private boolean checkTaskExistsInTrello(String taskName) throws InterruptedException {
        final String TRELLO_URL = "https://trello.com/login";
        WebDriver driverTrello = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driverTrello.get(TRELLO_URL);

        driverTrello.findElement(By.id("user")).sendKeys("twoj_login");
        driverTrello.findElement(By.id("password")).sendKeys("twoje_haslo");
        WebElement el = driverTrello.findElement(By.id("login"));
        el.submit();

        Thread.sleep(4000);

        driverTrello.findElement(By.id("password")).sendKeys("twoje_haslo");
        driverTrello.findElement(By.id("login-submit")).submit();

        Thread.sleep(4000);

        driverTrello.findElements(By.xpath("//a[@class=\"board-tile\"]")).stream()
                .filter(aHref ->
                        aHref.findElements(By.xpath(".//div[@title=\"Kodilla Application\"]")).size() > 0)
                .forEach(WebElement::click);

        Thread.sleep(4000);

        boolean result = driverTrello.findElements(By.xpath("//span")).stream()
                .anyMatch(theSpan -> theSpan.getText().equals(taskName));

        driverTrello.close();

        return result;
    }
}
