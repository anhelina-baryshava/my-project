package web.configuration;

import lombok.Data;
import web.configuration.WebDriverFactory;
@Data
public class WebConfig {
    private String baseUrl;
    private WebDriverFactory.Browser browser;
}
