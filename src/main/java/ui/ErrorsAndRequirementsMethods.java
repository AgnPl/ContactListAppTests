package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ErrorsAndRequirementsMethods {
    private static final Logger LOGGER = Logger.getLogger(ErrorsAndRequirementsMethods.class.getName());
    private final WebDriver driver;

    public ErrorsAndRequirementsMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void providingIncorrectCredentialToLogIn() {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("incorrect.email@host.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("WrongPassword");
    }

    public void clickingSubmitButtonToLogin() {
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void checkingVisibilityOfErrors() {
        WebElement error = driver.findElement(By.id("error"));
        assertFalse(error.getText().isBlank());
        LOGGER.info("Error message appeared correctly.");
    }

}
