package rest.services;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import io.restassured.response.ValidatableResponse;
import rest.pojos.CreateUserResponse;
import rest.pojos.UpdatedResponse;
import rest.pojos.UserPojoFull;
import rest.pojos.UserRequest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserService extends RestService {
    @Override
    protected String getBasePath() {
        return "/users";
    }

    public UserService(Cookies cookies) {
        super(cookies);
    }

    @Step("Create new user")
    @Attachment
    public CreateUserResponse createUser(UserRequest request) {
        return given().spec(REQUEST_SPECIFICATION).body(request).post().as(CreateUserResponse.class);
    }

    @Step("Get all users")
    public List<UserPojoFull> getUsers() {
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", UserPojoFull.class);
    }
    @Step("Get user with id {id}")
    public UserPojoFull getUser(int id){
        return  given().spec(REQUEST_SPECIFICATION).get("/" + id).jsonPath().getObject("data", UserPojoFull.class);
    }
    @Step("Update user with id {id}")
    public UpdatedResponse updateUser(int id){
        return  given().spec(REQUEST_SPECIFICATION).put("/" + id).as(UpdatedResponse.class);
    }
    @Step("Delete user with id {id}")
    public ValidatableResponse deleteUser(int id){
       return given().spec(REQUEST_SPECIFICATION).delete("/" + id).then().statusCode(204);
    }
}
