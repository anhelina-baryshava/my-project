package rest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import rest.pojos.UpdatedResponse;
import rest.pojos.UserRequest;
import rest.pojos.CreateUserResponse;
import rest.pojos.UserPojoFull;
import rest.utils.RestWrapper;
import rest.utils.UserGenerator;
import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("API tests")
@Feature("API for users")
@Tag("rest")
public class RestTest {
    private static RestWrapper api;

    @BeforeAll
    public static void prepareClient(){
        api = RestWrapper.loginAs("eve.holt@reqres.in", "cityslicka");
    }

    @AfterAll
    public static void logoutClient(){
        api = RestWrapper.logout();

    }
    @DisplayName("Getting all users")
    @Story("Get all users")
    @Test
    public void getUsers() {
        assertThat(api.user.getUsers()).extracting(UserPojoFull::getEmail).contains("george.bluth@reqres.in");
    }

    @DisplayName("Creating new user")
    @Story("Create new users")
    @Test
    public void createUser() {
        UserRequest request = UserGenerator.getSimpleUser();
        CreateUserResponse response = api.user.createUser(request);

        assertThat(response)
                .isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo(request.getName());
    }

    @DisplayName("Getting new user")
    @Test
    public void getUser() {
        int id = 1;
        assertThat(api.user.getUser(id)).extracting(UserPojoFull::getEmail).isEqualTo("george.bluth@reqres.in");
    }

    @DisplayName("Updating new user")
    @Description("Update user information")
    @Test
    public void updateUser() {
        int id = 1;
        assertThat(api.user.updateUser(id)).extracting(UpdatedResponse::getUpdatedAt).isNotNull();
    }
    @DisplayName("Deleting new user")
    @Test
    public void deleteUser() {
        int id = 1;
        api.user.deleteUser(id);
    }
}
