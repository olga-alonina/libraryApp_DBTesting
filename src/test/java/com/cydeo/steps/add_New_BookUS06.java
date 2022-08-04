package com.cydeo.steps;

import com.cydeo.pages.AddBook;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class add_New_BookUS06 {
    AddBook addBook = new AddBook();
    Actions actions = new Actions(Driver.getDriver());

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        addBook.addBook.click();
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String name) {
        addBook.bookName.sendKeys(name);

    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
        addBook.isbn.sendKeys(isbn);
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        addBook.year.sendKeys(year);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        addBook.author.sendKeys(author);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String value) {
        Select bookCategory = new Select(Driver.getDriver().findElement(By.id("book_group_id")));
        bookCategory.selectByVisibleText(value);
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        actions.moveToElement(addBook.save_changes).click().perform();
    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String conf_mess) {
        Assert.assertTrue(addBook.confirmationMess.isDisplayed());
        System.out.println("addBook.confirmationMess.getText() = " + addBook.confirmationMess.getText());
        Assert.assertEquals(addBook.confirmationMess.getText(), conf_mess);
    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String bookName) {
        DB_Util.createConnection();
        String addNewBook = "select id,name,author from books\n" +
                "where name = '" +bookName+ "'order by id desc";
        DB_Util.runQuery(addNewBook);
        System.out.println("DB_Util.getCellValue(1, name)"+DB_Util.getCellValue(1,"name"));
        Assert.assertEquals(DB_Util.getCellValue(1,"name"), bookName);

    }
}
