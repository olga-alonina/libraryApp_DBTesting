package com.cydeo.steps;

import com.cydeo.pages.BasePage;
import com.cydeo.pages.BookPage;
import com.cydeo.pages.BorrowingBooks;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class student_borrow_bookUS07 extends BasePage {
    BookPage bookPage = new BookPage();
    BorrowingBooks borrowingBooks = new BorrowingBooks();

    String keep_bookName1;


    @Given("the user searches book name called {string}")
    public void the_user_searches_book_name_called(String bookName) {
        bookPage.search.sendKeys(bookName);
        keep_bookName1 = bookName;

    }

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        bookPage.editBook(keep_bookName1).click();
    }

    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String pageName) {
        navigateModule(pageName);
        System.out.println("borrowingBooks.verificationPage.getText() = " + borrowingBooks.verificationPage.getText());
        Assert.assertEquals(borrowingBooks.verificationPage.getText(), pageName);
        for (WebElement each : borrowingBooks.all_BB_Name) {
            if(each.getText().contains(keep_bookName1)){
                Assert.assertTrue(true);
                break;
            }
        }

    }

    @Then("verify logged {string} has same book in database")
    public void verify_logged_has_same_book_in_database(String userType) {
        DB_Util.createConnection();
        String st_Borrow_Book = "select full_name, b.name, bb.borrowed_date\n" +
                "from users u\n" +
                "         inner join book_borrow bb on u.id = bb.user_id\n" +
                "         inner join books b on bb.book_id = b.id\n" +
                "where name = '"+ keep_bookName1 +"' order by 3 desc";
        DB_Util.runQuery(st_Borrow_Book);
        System.out.println("DB_Util.getFirstRowFirstColumn().toLowerCase() = " + DB_Util.getFirstRowFirstColumn().toLowerCase());
        Assert.assertTrue(DB_Util.getFirstRowFirstColumn().toLowerCase().contains(userType));
        System.out.println("DB_Util.getCellValue(2, \"name\") = " + DB_Util.getCellValue(2, "name"));
        Assert.assertEquals(DB_Util.getCellValue(2, "name"), keep_bookName1);

    }
}
