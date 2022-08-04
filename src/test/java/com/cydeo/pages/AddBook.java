package com.cydeo.pages;

import com.cydeo.utility.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddBook {
    Faker faker = new Faker();
    Actions actions = new Actions(Driver.getDriver());
    public AddBook() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css="#books [href='tpl/add-book.html']")
    public WebElement addBook;
    @FindBy(css = "#add_book_form [name='name']")
    public WebElement bookName;
    @FindBy(css = "#add_book_form [name='isbn']")
    public WebElement isbn;
    @FindBy(css = "#add_book_form [name='year']")
    public WebElement year;
    @FindBy(css = "#add_book_form [name='author']")
    public WebElement author;
    @FindBy( id="description")
    public WebElement description;

    @FindBy(id="tbl_books_info")
    public WebElement amountBooks;

    @FindBy(css = "[class='btn btn-primary']")
    public WebElement save_changes;

    @FindBy(css = "[class='toast-message']")
    public WebElement confirmationMess;

    public static void numberBooks(){
        AddBook addBook = new AddBook();
        String number = addBook.amountBooks.getText().substring(0,19);
        System.out.println("addBook.amountBooks.getText() = " + addBook.amountBooks.getText());
        String total = number.substring(number.length()-8);
        System.out.println("total = " + total);
    }
}
