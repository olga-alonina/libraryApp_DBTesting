package com.cydeo.steps;

import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class most_borrowedUS05 {
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        DB_Util.createConnection();
        String most_borrowed = "select bc.name, count(*)\n" +
                "from book_borrow bb\n" +
                "         inner join books b on bb.book_id = b.id\n" +
                "         inner join book_categories bc on b.book_category_id = bc.id\n" +
                "group by name\n" +
                "order by 2 desc";
        DB_Util.runQuery(most_borrowed);
    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String genre) {
        System.out.println("DB_Util.getFirstRowFirstColumn() = " + DB_Util.getFirstRowFirstColumn());
        Assert.assertEquals(DB_Util.getFirstRowFirstColumn(), genre);
    }
}
