package stellarburgers.tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

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
    public void successfulCreateUser() {
        String userData = "{ \"email\": \"asdsadsdsaddata@yandex.ru\", \"password\": \"password\", \"name\": \"ASDASDDAD\" }";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
}
