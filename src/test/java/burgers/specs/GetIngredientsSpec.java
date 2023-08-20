package burgers.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static burgers.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetIngredientsSpec {

    public static RequestSpecification ingredientsRequestSpec = with()
            .log().uri()
            .log().method()
            .filter(withCustomTemplates())
            .baseUri("https://stellarburgers.nomoreparties.site")
            .basePath("/api");

    public static ResponseSpecification ingredientsResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/ingredients-response-schemas.json"))
            .build();
}
