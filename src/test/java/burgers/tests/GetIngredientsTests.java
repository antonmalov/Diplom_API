package burgers.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static burgers.specs.GetIngredientsSpec.ingredientsRequestSpec;
import static burgers.specs.GetIngredientsSpec.ingredientsResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Tag("api")
public class GetIngredientsTests {

    @Test
    @DisplayName("Получение данных по доступным ингредиентам")
    @Description("Проверка возможности получения ингредиентов")
    public void getAllIngredients() {

        step("Отправка GET запроса для получения списка ингредиентов", () ->
                given(ingredientsRequestSpec)
                        .when()
                        .get("https://stellarburgers.nomoreparties.site/api/ingredients")
                        .then()
                        .spec(ingredientsResponseSpec));
    }
}
