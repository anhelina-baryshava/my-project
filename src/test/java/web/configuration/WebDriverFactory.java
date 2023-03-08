package web.configuration;

import com.google.common.io.Resources;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class WebDriverFactory {
    private static TestConfigFactory config = TestConfigFactory.getInstance();

    public static WebDriver getWebDriver() {
        switch (config.getWebConfig().getBrowser()) {
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
            default:
                return getChromeDriver();
        }
    }

    public enum Browser {
        CHROME, FIREFOX
    }

    private static ChromeDriver getChromeDriver() {
        String chromeBinaryName;

        switch(System.getProperty("os.name").toLowerCase()){
            case "mac os x":
            default:
                chromeBinaryName = "chromedriver";
            case "windows 7":
                chromeBinaryName = "chromedriver.exe";
        }
        System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver").getPath());
        return new ChromeDriver();
    }
}
