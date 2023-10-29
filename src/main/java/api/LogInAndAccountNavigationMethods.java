package api;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogInAndAccountNavigationMethods {

    private final static String TESTED_URL = "https://thinking-tester-contact-list.herokuapp.com/";
    private final static String POSTMAN_LiNK = "https://documenter.getpostman.com/view/4012288/TzK2bEa8";
    private final static String USER_EMAIL = "testing.email@string.com";
    private final static String PASSWORD = "TestingPassword123";
    private static final Logger LOGGER = Logger.getLogger(LogInAndAccountNavigationMethods.class.getName());
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LogInAndAccountNavigationMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToPage() {
        driver.get(TESTED_URL);
        String currentLink = driver.getCurrentUrl();
        assertEquals(TESTED_URL, currentLink);
        LOGGER.info("User is on tested page.");
    }

    public void clickingSingUpButton() {
        WebElement singUpButton = driver.findElement(By.id("signup"));
        singUpButton.click();
        LOGGER.info("Sing up button has been clicked.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        assertTrue(driver.findElement(By.id("firstName")).isDisplayed());
        LOGGER.info("User was redirected correctly to 'sign up' page.");
    }

    public void nameGeneratorToCreateAccount() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String nameGenerator = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys(nameGenerator);
        LOGGER.info("Created user's name: " + nameGenerator);
    }

    public void lastNameGeneratorToCreatedAccount() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String lastNameGenerator = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys(lastNameGenerator);
        LOGGER.info("Created user's last name: " + lastNameGenerator);
    }


    public void emailGeneratorToCreateAccount() {
        int length = 7;
        boolean useLetters = true;
        boolean useNumbers = true;
        String emailGenerator = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(emailGenerator + "@host.com");
        LOGGER.info("Created user's email : " + emailGenerator + "@host.com");
    }

    public void passwordGeneratorToCreateAccount() {
        int length = 12;
        boolean useLetters = true;
        boolean useNumbers = true;
        String passwordGenerator = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(passwordGenerator);
        LOGGER.info("Created user's password : " + passwordGenerator);
    }

    public void clickingSubmitButton() {
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-contact")));
        assertTrue(driver.findElement(By.id("add-contact")).isDisplayed());
        LOGGER.info("User is logged in and was redirected to dashboard page.");
    }

    public void logOut() {
        WebElement logOutButton = driver.findElement(By.id("logout"));
        logOutButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.welcome-message")));
        assertTrue(driver.findElement(By.cssSelector("div.welcome-message")).isDisplayed());
        LOGGER.info("User id logged out from account. He was redirected to main page.");
    }

    public void provideCredentialsToLogInToExistingAccount() {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(USER_EMAIL);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(PASSWORD);
    }

    public void checkingApiLinkRedirection() {
        WebElement apiLink = driver.findElement(
                By.xpath("//a[@href='https://documenter.getpostman.com/view/4012288/TzK2bEa8']"));
        apiLink.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(POSTMAN_LiNK));
        LOGGER.info("Link to API works correctly. User was redirected to Postman.");
    }
}
