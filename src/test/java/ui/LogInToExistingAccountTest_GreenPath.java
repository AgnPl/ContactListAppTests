package ui;

import api.LogInAndAccountNavigationMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class LogInToExistingAccountTest_GreenPath {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @Test
    public void logInToExistingAccount() {
        LogInAndAccountNavigationMethods logInAndCreateAccountMethods = new LogInAndAccountNavigationMethods(driver);

        logInAndCreateAccountMethods.goToPage();
        logInAndCreateAccountMethods.provideCredentialsToLogInToExistingAccount();
        logInAndCreateAccountMethods.clickingSubmitButton();
        logInAndCreateAccountMethods.logOut();
    }

    @AfterEach
    public void closingDriver() {
        driver.quit();
    }
}
