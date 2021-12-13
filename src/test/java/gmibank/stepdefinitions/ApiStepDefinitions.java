package gmibank.stepdefinitions;

import gmibank.pojos.Customer;
import gmibank.utilities.ReadTxt;
import gmibank.utilities.WriteToTxt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.ISO8601Utils;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiStepDefinitions {
    Response response;
    JsonPath jsonPath;
    Customer[] customers;

    @Given("user read all customer and sets response using to api endpoint {string}")
    public void user_read_all_customer_and_sets_response_using_to_api_endpoint(String endpoint_customer) {
      String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJncm91cDE2ZW1wbG95ZWUiLCJhdXRoIjoiUk9MRV9FTVBMT1lFRSIsImV4cCI6MTYzOTIwOTkyNn0.u-FObqtIogKoO7bLZ4LU_KBmFLOiD4OrLBmhQt3-a0sdQBitVzrTzZKHf3t3slsV-3GlT1KQBvaLhWpmCaGo2g";
        response = given().headers(
                "Authorization",
                "Bearer " + token,//ConfigurationReader.getProperty("api_bearer_token"),
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)//auth().oauth2(token).contentType(ContentType.JSON)
                .when()
                .get(endpoint_customer)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract()
                .response();
       // response.prettyPrint();
    }



  List<Object> expectedallSsn = new ArrayList<>();
    @Given("user deserialization customer data json to java pojo")
    public void user_deserialization_customer_data_json_to_java_pojo() throws IOException {

        List<Object> allEmail = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        customers = objectMapper.readValue(response.asString(), Customer[].class);
        for (int i = 0; i < customers.length; i++) {
            System.out.println(customers[i].getEmail());
            allEmail.add(customers[i].getEmail());
        }
        System.out.println(allEmail);
        System.out.println("*************************");
        List<Object> allLastName = new ArrayList<>();
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getLastName() != null) {
                allLastName.add(customers[i].getLastName());
            }
        }
        System.out.println(allLastName.size());

        System.out.println("*************************");

        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getSsn() != null){
            expectedallSsn.add(customers[i].getSsn());
        }}

    }

    @Then("user validates all data")
    public void user_validates_all_data() {
        System.out.println(expectedallSsn);
        WriteToTxt.saveDataInFileWithAllCustomerInfo("Allcustomerinfo1.txt",customers);
         WriteToTxt.saveDataInFile("AllSSNInfo1",customers);

         List<String>customerSsnList = ReadTxt.returnCustomerSNNList("AllSSNInfo1.txt");

        System.out.println(customerSsnList);
        System.out.println("********************");
        System.out.println(expectedallSsn);
        Assert.assertEquals(expectedallSsn.size(),customerSsnList.size(),"not equal");
    }
List<Object>allLastName=new ArrayList<>();
    @Given("user goes to {string}")
    public void user_goes_to(String end_point) throws IOException {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJncm91cDE2ZW1wbG95ZWUiLCJhdXRoIjoiUk9MRV9FTVBMT1lFRSIsImV4cCI6MTYzOTIwOTkyNn0.u-FObqtIogKoO7bLZ4LU_KBmFLOiD4OrLBmhQt3-a0sdQBitVzrTzZKHf3t3slsV-3GlT1KQBvaLhWpmCaGo2g";
        response = given().headers(
                        "Authorization",
                        "Bearer " + token,//ConfigurationReader.getProperty("api_bearer_token"),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)//auth().oauth2(token).contentType(ContentType.JSON)
                .when()
                .get(end_point)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract()
                .response();
        response.prettyPrint();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        customers = objectMapper.readValue(response.asString(), Customer[].class);
        for (int i = 0; i < customers.length; i++) {
         //   System.out.println(customers[i].getEmail());
            allLastName.add(customers[i].getEmail());
        }

    }
    List<Customer> customerLastName;
    @Given("user validates all data with new data")
    public void user_validates_all_data_with_new_data() throws Exception {
    //    System.out.println(allLastName);
        System.out.println(allLastName.size());
WriteToTxt.saveDataInFileLastName("allLastName.txt",customers);
customerLastName=ReadTxt.returnCustomerLastName("allLastName.txt");
        System.out.println(customerLastName.size());
        System.out.println(allLastName.size());
//Assert.assertEquals(allLastName,customerLastName);
    }

}