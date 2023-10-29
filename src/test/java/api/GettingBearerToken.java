package api;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

public class GettingBearerToken {

    public String gettingBearerToken() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        LogInAndAccountNavigationMethods logInAndCreateAccountMethods = new LogInAndAccountNavigationMethods(driver);

        logInAndCreateAccountMethods.goToPage();
        logInAndCreateAccountMethods.provideCredentialsToLogInToExistingAccount();
        logInAndCreateAccountMethods.clickingSubmitButton();

        Set<Cookie> cookies = driver.manage().getCookies();

        return getBearerToken(cookies);
    }

    private String getBearerToken(Set<Cookie> cookies) {
        String bearerToken = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                bearerToken = cookie.getValue();
                break;
            }
        }
        return bearerToken;
    }
}

