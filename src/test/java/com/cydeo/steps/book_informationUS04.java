package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.*;

public class book_informationUS04 {
    BookPage bookPage = new BookPage();
    Actions actions = new Actions(Driver.getDriver());
    List<String> db_book_informations;
    String keep_bookName;

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        actions.click(bookPage.search).sendKeys(bookName).perform();
        keep_bookName = bookName;
    }

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBook(keep_bookName).click();
    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        DB_Util.createConnection();
        String book_information = "select b.name , author, isbn, b.description, b.year,bc.id from books b\n" +
                "                                                join book_categories bc on b.book_category_id = bc.id\n" +
                "                                                where b.name = 'Clean Code' and  isbn = 999;";
        DB_Util.runQuery(book_information);
        db_book_informations = DB_Util.getRowDataAsList(1);

        System.out.println("db_book_informations = " + db_book_informations);

        for (WebElement each : bookPage.all_Info_About_Book) {//todo ask Mehmet
            System.out.println("each.getText()) = " + each.getAttribute("value"));
            Assert.assertTrue(db_book_informations.contains(each.getAttribute("value")));
        }
    }
}
