package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;

public class verificationBookCategoriesUS03 {

    BookPage bookPage = new BookPage();
    List<String> db_book_categories;
    List<WebElement> all_UI_Option;

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        Select bookCategories = new Select(bookPage.mainCategoryElement);

        all_UI_Option = bookCategories.getOptions().subList(1,21);//todo how to make it dynamic

        for (WebElement all : all_UI_Option) {
            System.out.println(all.getText());
        }

    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.createConnection();
        String book_categories = "select name from book_categories";
        DB_Util.runQuery(book_categories);
        db_book_categories = DB_Util.getColumnDataAsList(1);

        System.out.println("db_book_categories = " + db_book_categories);

        for (WebElement each : all_UI_Option) {
            Assert.assertTrue(db_book_categories.contains(each.getText()));
        }
    }
}
