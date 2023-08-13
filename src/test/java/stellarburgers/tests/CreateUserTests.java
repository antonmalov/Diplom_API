package stellarburgers.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.models.CreateResponseModel;
import stellarburgers.models.CreateUserModel;
import stellarburgers.models.UserGenerator;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        CreateResponseModel responseModel = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(createData)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(CreateResponseModel.class);

        assertEquals(createData.getEmail(), responseModel.getUser().getEmail());
    }
}
