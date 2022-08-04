package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class BorrowingBooks {
    public BorrowingBooks() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(css = "td:nth-child(2)")
    public List<WebElement> all_BB_Name;

    @FindBy(css = "h3")
    public WebElement verificationPage;
    }

