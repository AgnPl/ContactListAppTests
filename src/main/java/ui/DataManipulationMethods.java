package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataManipulationMethods {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger LOGGER = Logger.getLogger(DataManipulationMethods.class.getName());

    public DataManipulationMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickExistingContactToDelete() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myTable")));

        WebElement rowToDelete = driver.findElement(By.id("myTable"));
        rowToDelete.click();

        assertTrue(driver.findElement(By.id("delete")).isDisplayed());
        LOGGER.info("Contact has been clicked and user was redirected to details page.");
    }

    public void deleteContact() {
        WebElement deleteButton = driver.findElement(By.id("delete"));
        deleteButton.click();

        driver.switchTo().alert().accept();
        LOGGER.info("Confirmation alert was accepted.");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("contactTableHead"))));
        assertTrue(driver.findElement(By.className("contactTableHead")).isDisplayed());
        LOGGER.info("Contact has been deleted. User is redirected back to whole table view.");
    }

    public void clickingEditContactButton() {
        WebElement editButton = driver.findElement(By.id("edit-contact"));
        editButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        assertTrue(driver.findElement(By.id("firstName")).isDisplayed());
        LOGGER.info("User is on edit mode.");
    }

    public void editingContactData() {
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        firstNameField.sendKeys("John Paul");

        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        lastNameField.sendKeys("Smith-Douglas");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        cityField.sendKeys("Brazil");
    }

    public void applyingChangesInContacts() {
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        LOGGER.info("User's data was edited and applied.");

        WebElement returnToContactListButton = driver.findElement(By.id("return"));
        returnToContactListButton.click();

        assertTrue(driver.findElement(By.id("myTable")).isDisplayed());
        LOGGER.info("User is redirected to main page.");
    }
}