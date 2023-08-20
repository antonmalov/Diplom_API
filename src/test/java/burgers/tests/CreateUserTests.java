package burgers.tests;

import burgers.models.ErrorUserResponseModel;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import burgers.models.CreateUserResponseModel;
import burgers.models.CreateUserModel;
import burgers.models.UserGenerator;

import static burgers.specs.CreateUserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("api")
public class CreateUserTests {
    String userToken;

    @AfterEach
    public void deleteUser() {
        if (userToken != null) {
            given(userRequestSpec)
                    .header("authorization", userToken)
                    .when()
                    .delete("/user");
        }
    }

    @Test
    @DisplayName("Создание пользователя с корректными данными")
    @Description("Проверка успешного создания пользователя")
    public void successfulCreateUser() {
        CreateUserModel createData = UserGenerator.getRandomUser();

        CreateUserResponseModel responseModel = step("Создание пользователя", () ->
                given(userRequestSpec)
                        .body(createData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(successfulCreateUserResponseSpec)
                        .extract().as(CreateUserResponseModel.class));

        userToken = responseModel.getAccessToken();

        step("проверка результата", () -> {
            assertEquals(createData.getEmail(), responseModel.getUser().getEmail());
            assertEquals(createData.getName(), responseModel.getUser().getName());
        });
    }

    @Test
    @DisplayName("Создание пользователя с данными, которые уже есть в системе")
    @Description("Проверка невозможности создания двух одинаковых пользователей")
    public void notBeCreatedTwoEqualsUser() {
        CreateUserModel createData = UserGenerator.getRandomUser();

        step("Создание пользователя", () ->
                userToken = given(userRequestSpec)
                        .body(createData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(successfulCreateUserResponseSpec)
                        .extract().path("accessToken"));

        ErrorUserResponseModel alreadyExistModel = step("Создание дублирующегося пользователя", () ->
                given(userRequestSpec)
                        .body(createData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(ErrorResponseSpec)
                        .extract().as(ErrorUserResponseModel.class));

        step("проверка результата", () -> {
            assertEquals("false", alreadyExistModel.getSuccess());
            assertEquals("User already exists", alreadyExistModel.getMessage());
        });
    }
    @Test
    @DisplayName("Создание пользователя без пароля")
    @Description("Проверка невозможности создать пользователя без пароля")
    public void notBeCreatedWithMissingPassword() {
        CreateUserModel createData = UserGenerator.getUserWithoutPassword();
        String expectedMessage = "Email, password and name are required fields";

        ErrorUserResponseModel alreadyExistModel = step("Создание пользователя без пароля", () ->
                given(userRequestSpec)
                        .body(createData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(ErrorResponseSpec)
                        .extract().as(ErrorUserResponseModel.class));

        step("проверка результата", () -> {
            assertEquals("false", alreadyExistModel.getSuccess());
            assertEquals(expectedMessage, alreadyExistModel.getMessage());
        });
    }

    @Test
    @DisplayName("Создание пользователя без имени")
    @Description("Проверка невозможности создать пользователя без имени")
    public void notBeCreatedWithMissingName() {
        CreateUserModel createData = UserGenerator.getUserWithoutName();
        String expectedMessage = "Email, password and name are required fields";

        ErrorUserResponseModel alreadyExistModel = step("Создание пользователя без имени", () ->
                given(userRequestSpec)
                        .body(createData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(ErrorResponseSpec)
                        .extract().as(ErrorUserResponseModel.class));

        step("проверка результата", () -> {
            assertEquals("false", alreadyExistModel.getSuccess());
            assertEquals(expectedMessage, alreadyExistModel.getMessage());
        });
    }

    @Test
    @DisplayName("Создание пользователя без почты")
    @Description("Проверка невозможности создать пользователя без почты")
    public void notBeCreatedWithMissingEmail() {
        CreateUserModel createData = UserGenerator.getUserWithoutEmail();
        String expectedMessage = "Email, password and name are required fields";

        ErrorUserResponseModel alreadyExistModel = step("Создание пользователя без почты", () ->
                given(userRequestSpec)
                        .body(createData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(ErrorResponseSpec)
                        .extract().as(ErrorUserResponseModel.class));

        step("проверка результата", () -> {
            assertEquals("false", alreadyExistModel.getSuccess());
            assertEquals(expectedMessage, alreadyExistModel.getMessage());
        });
    }
}
