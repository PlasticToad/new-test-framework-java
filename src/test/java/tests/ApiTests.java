package tests;

import controllers.UserController;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.AddUserResponse;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static testdata.TestData.DEFAULT_USER;
import static testdata.TestData.INVALID_USER;

@Story("API tests")
@Tag("api")
public class ApiTests {
    UserController userController = new UserController();

    static Stream<User> users() {return Stream.of(DEFAULT_USER, INVALID_USER);}

    @ParameterizedTest
    @MethodSource("users")
    void paramCreateUserControllerTest(User user) {

        Response response = userController.createUser(user);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }

    @Test
    void simpleCreateUserControllerTest() {

        Response response = userController.createUser(DEFAULT_USER);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }
}
