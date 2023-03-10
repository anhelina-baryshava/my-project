package rest.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;

public abstract class RestService {
    private static final String BASE_URL = "https://reqres.in/api";
    protected RequestSpecification REQUEST_SPECIFICATION;
    protected Cookies cookies;

    protected abstract String getBasePath();

    public RestService(Cookies cookies) {
        this.cookies = cookies;

        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .addCookies(cookies)
                .setBaseUri(BASE_URL)
                .setBasePath(getBasePath())
                .setContentType(ContentType.JSON)
                .build();
    }
}
