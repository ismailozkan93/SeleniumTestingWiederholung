package gmibank.stepdefinitions;

import gmibank.utilities.DatabaseUtility;
import gmibank.utilities.ReadTxt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class DatabaseStepDefinitions {
    List<Object>allColumnData =new ArrayList<>();
    @Given("user connect database and print all data")
    public void user_connect_database_and_print_all_data() {
        DatabaseUtility.createConnection();
        String  query = "SELECT * FROM public.tp_customer";
        List<Object> list = DatabaseUtility.getColumnData(query, "id");

        for (Object w : list) {
            System.out.println(w.toString());
        }
       // List<String>customerSsnList= ReadTxt.returnCustomerSNNList("ALLSsnInfo2.txt");
        //System.out.println(customerSsnList.size());
        System.out.println();
        System.out.println(list.size());


    }


}

