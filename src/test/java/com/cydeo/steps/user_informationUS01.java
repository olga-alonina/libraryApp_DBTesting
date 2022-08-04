package com.cydeo.steps;

import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;


public class user_informationUS01 {
    String actual_result;
    List<String> list_actual_result;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_Util.createConnection();
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String queryId = "select count(id) from users";
        DB_Util.runQuery(queryId);
        actual_result = DB_Util.getFirstRowFirstColumn();
        System.out.println("actual_result = " + actual_result);
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String unigueId = "select count(distinct id) from users";
        DB_Util.runQuery(unigueId);
        String expected_result = DB_Util.getFirstRowFirstColumn();
        System.out.println("expected_result = " + expected_result);
        Assert.assertEquals(actual_result, expected_result);
    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        String allColumn = "select * from users;";
        DB_Util.runQuery(allColumn);
        list_actual_result = DB_Util.getAllColumnNamesAsList();
        System.out.println("list_actual_result = " + list_actual_result);
    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> list_expected_result) {
        System.out.println("list_expected_result = " + list_expected_result);
        Assert.assertEquals(list_actual_result, list_expected_result);
    }
}
