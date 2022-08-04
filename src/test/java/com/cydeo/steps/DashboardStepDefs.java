package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DashboardStepDefs
{
    String actualUserNumbers;
    String actualBookNumbers;
    String actualBorrowedBookNumbers;
    LoginPage loginPage=new LoginPage();
    DashBoardPage dashBoardPage=new DashBoardPage();


    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginPage.login(user);
         BrowserUtil.waitFor(4);
    }
    @When("user gets all information from modules")
    public void user_gets_all_information_from_modules() {

        actualUserNumbers = dashBoardPage.usersNumber.getText();
        System.out.println("actualUserNumbers = " + actualUserNumbers);
        actualBookNumbers = dashBoardPage.booksNumber.getText();
        System.out.println("actualBookNumbers = " + actualBookNumbers);
        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);

    }


    @Then("the informations should be same with database")
    public void the_Informations_Should_Be_Same_With_Database() {
// make a conn
        DB_Util. createConnection();
        //users
        System.out.println("Users");
       //run query
        DB_Util.runQuery("select count(*) from users;");
       //store data
String expectedUsers = DB_Util.getFirstRowFirstColumn();

       //make assertion users
        Assert.assertEquals(expectedUsers, actualUserNumbers);

        //book
        System.out.println("Books");
                 //run query
        DB_Util.runQuery("select count(*) from books;");
        //store data
        String expectedBooks = DB_Util.getFirstRowFirstColumn();
        //make assertion users
        Assert.assertEquals(expectedBooks, actualBookNumbers);

        //borrowed book
        System.out.println("Borrowed Books");
        //run query
        DB_Util.runQuery("select count(*) from book_borrow where is_returned = 0;;");
        //store data
        String expectedBorBooks= DB_Util.getFirstRowFirstColumn();
        //make assertion users
        Assert.assertEquals(expectedBorBooks, actualBorrowedBookNumbers);

       //close the connection
        DB_Util.destroy();
    }
}
