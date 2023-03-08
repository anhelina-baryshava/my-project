package web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import web.configuration.WebDriverFactory;
import web.pages.LoginPage;


@DisplayName("UI tests for Wiki")
@ExtendWith(ScreenshotExtension.class)
public class WikiTest {

    private static final String login = "TestingTest998";
    private static final String password = "StrongPassword999";
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();// обертка которая создает новый инстанс на каждый поток

    @BeforeEach
    public void createDriver() {
        driver.set(WebDriverFactory.getWebDriver());
    }


    @DisplayName("happy path login")
    @Test
    public void login() {
        Assertions.assertTrue(
                new LoginPage(driver.get())
                        .loginAs(login, password)
                        .isLoggedIn(login)
        );
    }

    @DisplayName("login with invalid data")
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

