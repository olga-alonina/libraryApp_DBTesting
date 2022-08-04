package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.ConfigurationReader;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class borrowed_book_numberUS02 {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String ui_numberBB;
    String db_numberBB;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {
        Driver.getDriver().get(ConfigurationReader.getProperty("library_url"));
        loginPage.login(userType);
    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        ui_numberBB = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("ui_numberBB = " + ui_numberBB);
    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DB_Util.createConnection();
        String db_bb_number = "select count(*) from book_borrow bb where returned_date is null";
        DB_Util.runQuery(db_bb_number);
        db_numberBB = DB_Util.getCellValue(1, 1);
        System.out.println("db_numberBB = " + db_numberBB);

        Assert.assertEquals(ui_numberBB, db_numberBB);

    }
}

