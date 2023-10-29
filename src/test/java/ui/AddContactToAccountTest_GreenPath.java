package ui;

import api.LogInAndAccountNavigationMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileNotFoundException;
import java.time.Duration;

public class AddContactToAccountTest_GreenPath {

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
    public void logInToExistingAccount() throws FileNotFoundException {
        LogInAndAccountNavigationMethods logInAndCreateAccountMethods = new LogInAndAccountNavigationMethods(driver);
        AddContactMethods addContactMethods = new AddContactMethods(driver);

        logInAndCreateAccountMethods.goToPage();
        logInAndCreateAccountMethods.provideCredentialsToLogInToExistingAccount();
        logInAndCreateAccountMethods.clickingSubmitButton();
        addContactMethods.addingNewContact();
        addContactMethods.fillInName();
        addContactMethods.fillInLastName();
        addContactMethods.fillInDateOfBirth();
        addContactMethods.fillInPhone();
        addContactMethods.fillInEmail();
        addContactMethods.fillInFirstAddress();
        addContactMethods.fillInSecondAddress();
        addContactMethods.fillInCountry();
        addContactMethods.filInCity();
        addContactMethods.fillInPostalCode();
        addContactMethods.fillInProvince();
        logInAndCreateAccountMethods.clickingSubmitButton();
        logInAndCreateAccountMethods.logOut();
    }

    @AfterEach
    public void closingDriver() {
        driver.quit();
    }
}

