package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage extends BasePage{
    private By newLoginInput = By.id("wpName2");

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }
}
