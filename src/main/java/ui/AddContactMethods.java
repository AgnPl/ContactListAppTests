package ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class AddContactMethods {
    private static final Logger LOGGER = Logger.getLogger(AddContactMethods.class.getName());
    private final WebDriver driver;

    public AddContactMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void addingNewContact() {
        WebElement addNewContactButton = driver.findElement(By.id("add-contact"));
        addNewContactButton.click();
    }

    public void fillInName() {
        int length = 8;
        boolean useLetters = true;
        boolean useNumbers = false;
        String nameGenerator = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys(nameGenerator);

        LOGGER.info("Created user's name: " + nameGenerator);
    }

    public void fillInLastName() {
        int length = 7;
        boolean useLetters = true;
        boolean useNumbers = false;
        String lastNameGenerator = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement clientLastNameField = driver.findElement(By.id("lastName"));
        clientLastNameField.sendKeys(lastNameGenerator);

        LOGGER.info("Created user's name: " + lastNameGenerator);
    }

    public void fillInEmail() {
        int length = 8;
        boolean useLetters = true;
        boolean useNumbers = true;
        String emailGenerator = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(emailGenerator + "@host.com");
        LOGGER.info("Created user's email : " + emailGenerator + "@host.com");
    }

    public void fillInDateOfBirth() {
        Random random = new Random();
        int year = random.nextInt(1900, 2023);
        int month = random.nextInt(1, 12);
        int day = random.nextInt(1, 28);

        String formattedMonth;

        if (month <= 9) {
            formattedMonth = "0" + month;
        } else {
            formattedMonth = String.valueOf(month);
        }

        String formattedDay;

        if (day <= 9) {
            formattedDay = "0" + day;
        } else {
            formattedDay = String.valueOf(day);
        }

        WebElement dayOfBirthField = driver.findElement(By.id("birthdate"));
        dayOfBirthField.sendKeys(year + "-" + formattedMonth + "-" + formattedDay);
        LOGGER.info("Generated DoB: " + year + "-" + formattedMonth + "-" + formattedDay);
    }

    public void fillInPhone() {
        int length = 9;
        boolean useLetters = false;
        boolean useNumbers = true;
        String phoneNumber = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement phoneNumberField = driver.findElement(By.id("phone"));
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void fillInFirstAddress() {
        int length = 6;
        boolean useLetters = true;
        boolean useNumbers = true;
        String streetString = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement streetField = driver.findElement(By.id("street1"));
        streetField.sendKeys(streetString);
    }

    public void fillInSecondAddress() {
        int length = 5;
        boolean useLetters = true;
        boolean useNumbers = true;
        String streetString = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement streetField = driver.findElement(By.id("street2"));
        streetField.sendKeys(streetString);
    }

    public void filInCity() {
        int length = 9;
        boolean useLetters = true;
        boolean useNumbers = false;
        String streetString = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys(streetString);
    }

    public void fillInProvince() {
        int length = 8;
        boolean useLetters = true;
        boolean useNumbers = false;
        String provinceOrProvinceName = RandomStringUtils.random(length, useLetters, useNumbers);

        WebElement provinceOrProvinceField = driver.findElement(By.id("stateProvince"));
        provinceOrProvinceField.sendKeys(provinceOrProvinceName);
    }

    public void fillInPostalCode() {
        Random random = new Random();
        int firstPart = random.nextInt(10, 99);
        int secondPart = random.nextInt(100, 999);

        String postalCode = firstPart + "-" + secondPart;

        WebElement postalCodeField = driver.findElement(By.id("postalCode"));
        postalCodeField.sendKeys(postalCode);
    }

    public void fillInCountry() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("C:/Users/Aga/IdeaProjects/ContactListAppTests/src/test/resources/countriesInEurope.yaml");
        Yaml yaml = new Yaml();
        List<String> countries = yaml.load(inputStream);

        Random random = new Random();
        int randomCountryToFillIn = random.nextInt(countries.size());
        String selectedCountry = countries.get(randomCountryToFillIn);

        WebElement countryField = driver.findElement(By.id("country"));
        countryField.sendKeys(selectedCountry);
    }
}
