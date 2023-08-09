package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.hc.core5.util.Asserts;
import org.junit.Assert;

import java.util.List;

public class UserStepDefs_us01 {

    String actualUserCount;
    List<String> actualColumnList;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        // Make a connection with library
        // DB_Util.createConnection();
        System.out.println("--------------------------------------------------");
        System.out.println("--- CONNECTION WILL BE DONE WITH BEFORE HOOK -----");
        System.out.println("--------------------------------------------------");

    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        String query="select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUserCount);

    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {

        String query="select count(distinct id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);

        Assert.assertEquals(expectedUserCount,actualUserCount);

        //Close Conn
        // DB_Util.destroy();
        System.out.println("--------------------------------------------------");
        System.out.println("--- CONNECTION WILL BE CLOSED WITH AFTER HOOK -----");
        System.out.println("--------------------------------------------------");

    }


    //US01

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        String query = "select * from users";

        DB_Util.runQuery(query);

        actualColumnList = DB_Util.getAllColumnNamesAsList();
        // System.out.println("actualColumnList = " + actualColumnList);

    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedColumnList) {

         System.out.println("expectedColumnList = " + expectedColumnList);

        Assert.assertEquals( expectedColumnList, actualColumnList );


    }


}
