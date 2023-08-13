package stellarburgers.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.models.CreateResponseModel;
import stellarburgers.models.CreateUserModel;
import stellarburgers.models.UserGenerator;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static stellarburgers.helpers.CustomAllureListener.withCustomTemplates;

public class CreateUserTests {

    @Test
    public void getAllOrders() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://stellarburgers.nomoreparties.site/api/orders/all")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("orders[0].name", is("Флюоресцентный бургер"));
    }
    @Test
    @DisplayName("Создание пользователя с корректными данными")
    @Description("Проверка, что пользователь успешно создается")
    public void successfulCreateUser() {
        CreateUserModel createData = UserGenerator.getRandomUser();

        CreateResponseModel responseModel = step("Создание пользователя", () ->
                given()
                        .log().uri()
                        .log().body()
                        .filter(withCustomTemplates())
                        .contentType(JSON)
                        .body(createData)
                        .when()
                        .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(CreateResponseModel.class));

        step("проверка результата", () -> {
            assertEquals(createData.getEmail(), responseModel.getUser().getEmail());
            assertEquals(createData.getName(), responseModel.getUser().getName());
        });

    }
}
