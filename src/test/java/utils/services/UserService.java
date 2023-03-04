package utils.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.ValidatableResponse;
import pojos.UpdatedResponse;
import pojos.UserRequest;
import pojos.CreateUserResponse;
import pojos.UserPojoFull;

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

    public CreateUserResponse createUser(UserRequest request) {
        return given().spec(REQUEST_SPECIFICATION).body(request).post().as(CreateUserResponse.class);
    }

    public List<UserPojoFull> getUsers() {
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", UserPojoFull.class);
    }
    public UserPojoFull getUser(int id){
        return  given().spec(REQUEST_SPECIFICATION).get("/" + id).jsonPath().getObject("data", UserPojoFull.class);
    }
    public UpdatedResponse updateUser(int id){
        return  given().spec(REQUEST_SPECIFICATION).put("/" + id).as(UpdatedResponse.class);
    }
    public ValidatableResponse deleteUser(int id){
       return given().spec(REQUEST_SPECIFICATION).delete("/" + id).then().statusCode(204);
    }
}
