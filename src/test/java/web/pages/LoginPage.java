package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    private By openLogin = By.xpath("//span[contains(text(), 'Войти')]");
    private By loginInput = By.id("wpName1");
    private By passwordInput = By.id("wpPassword1");
    private By continueBtn = By.id("wpLoginAttempt");
    private By joinProjectBtn = By.id("mw-createaccount-join");
    private By errorMassage = By.xpath("//*[contains(@class, 'mw-message-box-error mw-message-box')]");
    private String errorMassageText = "Введены неверные имя участника или пароль. Попробуйте ещё раз.";
    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(BASE_URL);
        waitAndClick(openLogin);
        waitVisibility(loginInput);
    }

    public LoginPage typeLogin(String login){
        driver.findElement(loginInput).sendKeys(login);
        return this;
    }

    public LoginPage typePassword(String password){
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    public LoginPage clickContinueWithInvalid(){
        driver.findElement(continueBtn).click();
        return this;
    }

    public WelcomePage clickContinue(){
        driver.findElement(continueBtn).click();
        return new WelcomePage(driver);
    }

    public CreateAccountPage clickJoinProject(){
        driver.findElement(joinProjectBtn).click();
        return new CreateAccountPage(driver);
    }

    public WelcomePage loginAs(String login, String password){
        typeLogin(login);
        typePassword(password);
        return clickContinue();
    }

    public boolean isErrorMessagePresent() {
        try {
            return waitVisibility(errorMassage).getText().equals(errorMassageText);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }




}
