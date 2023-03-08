package web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import web.configuration.WebDriverFactory;
import web.pages.LoginPage;


public class WikiTest {

    private static final String login = "TestingTest998";
    private static final String password = "StrongPassword999";
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();// обертка которая создает новый инстанс на каждый поток


    @BeforeEach
    public void createDriver() {
        driver.set(WebDriverFactory.getWebDriver());
    }

    @AfterEach
    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

    @Test
    public void login() {
        Assertions.assertTrue(
                new LoginPage(driver.get())
                        .loginAs(login, password)
                        .isLoggedIn(login)
        );
    }

    @Test
    public void invalidLogin() {
        Assertions.assertTrue(
                new LoginPage(driver.get())
                        .typeLogin("bla")
                        .typePassword("blabla")
                        .clickContinueWithInvalid()
                        .isErrorMessagePresent());
    }


//    @Test
//    public void createNewAccountPage() {
//        Assertions.assertTrue(
//                new LoginPage(driver.get())
//                        .clickJoinProject()
//
//    }
//
//    }
}
//        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1)); // безусловное ожидание



//quit - закрывает сессию, close -не закрывает сессию, только окно

