package gmibank.stepdefinitions;

import gmibank.pojos.Customer;
import gmibank.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.json.Json;

import static io.restassured.RestAssured.given;

public class CountryapiStepDefinitions {

    Response responseall;
    JsonPath jsonPath;
    Customer[] customers;
    String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJncm91cDE2ZW1wbG95ZWUiLCJhdXRoIjoiUk9MRV9FTVBMT1lFRSIsImV4cCI6MTYzOTIwOTkyNn0.u-FObqtIogKoO7bLZ4LU_KBmFLOiD4OrLBmhQt3-a0sdQBitVzrTzZKHf3t3slsV-3GlT1KQBvaLhWpmCaGo2g";
    @Given("user sets the response using api en point {string}")
    public void user_sets_the_response_using_api_en_point(String endpoint_country) {

        responseall= given().headers(
                        "Authorization",
                        "Bearer " + token,//ConfigurationReader.getProperty("api_bearer_token"),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)//auth().oauth2(token).contentType(ContentType.JSON)
                .when()
                .get(endpoint_country)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract()
                .response();
        // responseall.prettyPrint();
        JsonPath jsonPath=responseall.jsonPath();
        System.out.println(jsonPath.getString("id"));
        Assert.assertFalse(jsonPath.getString("id").contains("113840"));
    }

    @Given("creates {string} and {string}")
    public void creates_and(String type, String country) {
    String newCountry="\n" +
            "    {\n" +
            "        \"name\": \"China\",\n" +
            "    }";
        responseall = given().headers(
                        "Authorization",
                        "Bearer " + token,//ConfigurationReader.getProperty("api_bearer_token"),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)//auth().oauth2(token).contentType(ContentType.JSON)
                .when()
                .body("{\""+type+"\":\""+country+"\"}")
                //.body(idJson+nameJson)
                .post("https://www.gmibank.com/api/tp-countries")
                .then()
                .contentType(ContentType.JSON)
               // .statusCode(200)
                .extract()
                .response();
        responseall.prettyPrint();
    }
    @Given("user saves the countries to correspondet files with new data")
    public void user_saves_the_countries_to_correspondet_files_with_new_data() {
     JsonPath jsonPath=responseall.jsonPath();
        System.out.println(jsonPath.getString("id"));
        Assert.assertTrue(jsonPath.getString("id").contains("113843"));

    }


    @Given("user deletes a country using endpoint {string} and delete {string}")
    public void user_deletes_a_country_using_endpoint_and_delete(String endpoint, String id) {
        responseall=given().headers(
                        "Authorization",
                        "Bearer " + token,//ConfigurationReader.getProperty("api_bearer_token"),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)//auth().oauth2(token).contentType(ContentType.JSON)
                .when()
                .delete(endpoint+id)
                .then()
                .contentType(ContentType.JSON)
                // .statusCode(200)
                .extract()
                .response();
//        responseall.prettyPrint();
    }


/*



    @Given("user deletes a country using endpoint {string} and its extension {string}")
    public void user_deletes_a_country_using_endpoint_and_its_extension(String endpoint, String id) {
    Response responsedel= given().headers(
            "Authorization",
            "Bearer " + ConfigurationReader.getProperty("api_bearer_token"),
            "Content-Type",
            ContentType.JSON,
            "Accept",
            ContentType.JSON)
            .when()
            .delete(endpoint+id)
            .then()
            .extract()
            .response();
        responsedel.prettyPrint();

        
        JsonPath jsonPath=responseall.jsonPath();
        String id1=jsonPath.getString("id");


        Assert.assertFalse("not verify",id1.contains(id));


    }
*/
}
