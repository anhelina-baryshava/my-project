package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePage{
    private By loggedText = By.xpath("//span[text()='TestingTest998']");
    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedIn(String login) {
        try {
            return waitVisibility(loggedText).getText().equals(login);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
}
