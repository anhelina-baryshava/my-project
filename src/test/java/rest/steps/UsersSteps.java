package rest.steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import rest.pojos.UserRequest;
import rest.pojos.CreateUserResponse;
import rest.pojos.UserPojoFull;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersSteps {
    private CreateUserResponse user;

    private static final RequestSpecification REQUEST_SPECIFICATION =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build();

    public CreateUserResponse createUser(UserRequest request) {
        user = given().body(request).post().as(CreateUserResponse.class);
        return user;
    }

    public UserPojoFull getUser() {
        return given().get("/" + user.getId()).as(UserPojoFull.class);
    }

    public static UserPojoFull getUser(int id) {
        return given().get("/" + id).as(UserPojoFull.class);
    }

    public static List<UserPojoFull> getUsers() {
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", UserPojoFull.class);
    }

}
