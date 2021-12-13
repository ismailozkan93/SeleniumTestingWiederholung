package gmibank.stepdefinitions;

import gmibank.pojos.Country;
import gmibank.pojos.Customer;
import gmibank.utilities.DatabaseUtility;
import gmibank.utilities.PDFGenerator;
import gmibank.utilities.ReadTxt;
import gmibank.utilities.WriteToTxt;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class Database1StepDefinitions {
    List<Object> list;
    @Given("user creates a connection with db using {string},{string} and {string}")
    public void user_creates_a_connection_with_db_using_and(String db_url, String username, String password) {
        DatabaseUtility.createConnection(db_url,username,password);
    }
    @Given("user provides the query as {string} and {string}")
    public void user_provides_the_query_as_and(String query, String column) {
        list = DatabaseUtility.getColumnData(query, column);
        System.out.println(list);
    }

    @And("user validates all db Customer Info")
    public void user_validates_all_db_Customer_Info(){
        String filename="ALLCustomerIDs.txt";
        WriteToTxt.saveAllCustomersComingFromDB(filename,list);
        System.out.println(list);
        System.out.println("******");
        List<String> listId=ReadTxt.returnCustomerSNNList("AllSSNInfo1");
        System.out.println(listId);
        System.out.println(listId.size());
        List<Object>ValidateList=new ArrayList<>();
        ValidateList.add("108-53-6655");
        ValidateList.add("224-71-4004");
        ValidateList.add("398-56-4356");
        System.out.println(ValidateList);
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertTrue(listId.size()==380);
        softAssert.assertTrue(listId.containsAll(ValidateList));
        softAssert.assertAll();
    }
    @Then("user prints them on the pdf and close connection")
    public void user_prints_them_on_the_pdf_and_close_connection() {
      //  System.out.println(list);
        //System.out.println("***********************");
        System.out.println("************");
        // System.out.println(list.get(5));
    }

    List<Object>listall;
    @Given("user provides the query as {string} and {string} and {string}")
    public void user_provides_the_query_as_and_and(String query, String columnName, String filename) {
       listall= DatabaseUtility.getColumnData(query,columnName);
  //      WriteToTxt.saveDifferentInfoFromDB(filename,listall);
    }


    @Then("user validates all db Customer Info2")
    public void user_validates_all_db_customer_info2() {
        List<Customer>list=new ArrayList<>();
        System.out.println(list);
        System.out.println("*******************");
        List<Object> SSNs = ReadTxt.returnAllComingFromDB("customerSSN.txt");
        List<Object> names = ReadTxt.returnAllComingFromDB("customerFirstName.txt");
        List<Object> zipCodes = ReadTxt.returnAllComingFromDB("customerZipCode.txt");
        List<Object> countryName = ReadTxt.returnAllComingFromDB("countrySSN.txt");
        List<Object> stateName = ReadTxt.returnAllComingFromDB("stateNames.txt");


        for(int i=0;i<5;i++){
            Customer customer=new Customer();
            Country country=new Country();
            customer.setSsn(SSNs.get(i).toString());
            customer.setFirstName(names.get(i).toString());
            customer.setZipCode(zipCodes.get(i).toString());
            customer.setStates(stateName.get(i).toString());
            country.setName(countryName.get(i).toString());

            customer.setCountry(country);
            list.add(customer);
        }
        System.out.println(list);


        PDFGenerator.pdfGeneratorRowsAndCellsWithList("Allcustomers",list,"AllCustomerInfo.pdf");


    }}


















//}








    /*
    List<Object>allColumnData = new ArrayList<>();
    @Given("user provides the query as {string} and {string}")
    public void user_provides_the_query_as_and(String query, String columnName) {

}
    @Given("user validates all db data")
    public void user_validates_all_db_data() {

    }
    @Then("user prints them on the pdf and close connection")
    public void user_prints_them_on_the_pdf_and_close_connection() {

    }

    @Given("user gets all customer column data using {string} and {string}")
    public void user_gets_all_customer_column_data_using_and(String query, String columnName) {
        allColumnData = DatabaseUtility.getColumnData(query, columnName);
        System.out.println(allColumnData);
    }

    @Given("user sets all customer info to correspondent files")
    public void user_sets_all_customer_info_to_correspondent_files() {
        String filename="allCustomerIds.txt";
         WriteToTxt.saveAllCustomersComingFromDB(filename,allColumnData);


    }
    @Then("user validates all db Customer info")
    public void user_validates_all_db_customer_info() {
        String filename="allCustomerIds.txt";
       // List<Object> list= ReadTxt.returnCustomerIds("allCustomerIds.txt");

    }
*/
