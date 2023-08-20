package burgers.tests;

import burgers.models.CreateUserModel;
import burgers.models.DeleteUserResponseModel;
import burgers.models.UserGenerator;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static burgers.specs.CreateUserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("api")
public class DeleteUserTests {

    @Test
    @DisplayName("Удаление пользователя")
    @Description("Проверка успешного удаления пользователя")
    public void successfulDeleteUser() {
        CreateUserModel createData = UserGenerator.getRandomUser();

        String token = step("Создание пользователя", () ->
                given(userRequestSpec)
                        .body(createData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(successfulCreateUserResponseSpec)
                        .extract().path("accessToken"));

        DeleteUserResponseModel deleteModel = step("Удаление пользователя", () ->
                given(userRequestSpec)
                        .header("authorization", token)
                        .when()
                        .delete("/user")
                        .then()
                        .spec(deleteUserResponseSpec)
                        .extract().as(DeleteUserResponseModel.class));

        step("Проверка результата", () -> {
            assertEquals("true", deleteModel.getSuccess());
            assertEquals("User successfully removed", deleteModel.getMessage());
        });
    }

    @Test
    @DisplayName("Удаление несуществуюшего пользователя")
    @Description("Проверка невозможности удаления несуществующего пользователя")
    public void unsuccessfulDeleteNonExistUser() {
        DeleteUserResponseModel deleteModel = step("Удаление несуществующего пользователя", () ->
                given(userRequestSpec)
                        .header("authorization", "sdfsdffsdfdsf")
                        .when()
                        .delete("/user")
                        .then()
                        .spec(deleteNonExistUserResponseSpec)
                        .extract().as(DeleteUserResponseModel.class));

        step("Проверка результата", () -> {
            assertEquals("false", deleteModel.getSuccess());
            assertEquals("You should be authorised", deleteModel.getMessage());
        });
    }
}
