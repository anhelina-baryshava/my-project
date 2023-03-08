package rest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rest.pojos.UpdatedResponse;
import rest.pojos.UserRequest;
import rest.pojos.CreateUserResponse;
import rest.pojos.UserPojoFull;
import rest.utils.RestWrapper;
import rest.utils.UserGenerator;


import static org.assertj.core.api.Assertions.assertThat;
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
    @Test
    public void getUsers() {
        assertThat(api.user.getUsers()).extracting(UserPojoFull::getEmail).contains("george.bluth@reqres.in");
    }

    @Test
    public void createUser() {
        UserRequest request = UserGenerator.getSimpleUser();
        CreateUserResponse response = api.user.createUser(request);

        assertThat(response)
                .isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo(request.getName());
    }

    @Test
    public void getUser() {
        int id = 1;
        System.out.println(api.user.getUser(id));
        assertThat(api.user.getUser(id)).extracting(UserPojoFull::getEmail).isEqualTo("george.bluth@reqres.in");
    }
    @Test
    public void updateUser() {
        int id = 1;
        System.out.println(api.user.updateUser(id));
        assertThat(api.user.updateUser(id)).extracting(UpdatedResponse::getUpdatedAt).isNotNull();
    }
    @Test
    public void deleteUser() {
        int id = 1;
        api.user.deleteUser(id);
    }
}
