package rest.utils;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import rest.pojos.UserLogin;
import rest.services.OrderService;
import rest.services.UserService;

import static io.restassured.RestAssured.given;

public class RestWrapper {
    private static final String BASE_URL = "https://reqres.in/api";
    private Cookies cookies;

    public UserService user;
    public OrderService order;

    private RestWrapper(Cookies cookies){
        this.cookies = cookies;

        user = new UserService(cookies);
        order = new OrderService(cookies);
    }

    public static RestWrapper loginAs(String login, String password){
        Cookies cookies = given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("/login")
                .body(new UserLogin(login, password))
                .post()
                .getDetailedCookies();

        return new RestWrapper(cookies);
    }

    public static RestWrapper logout(){
        Cookies cookies = given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("/logout")
                .post()
                .getDetailedCookies();

        return new RestWrapper(cookies);
    }
}
