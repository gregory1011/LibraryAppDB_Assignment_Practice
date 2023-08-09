package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class BookCategoryStepDefs_us03 {

    BookPage bookPage = new BookPage();
    List<String> actualCategories;

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {

        bookPage.navigateModule(moduleName);
        BrowserUtil.waitFor(3);

    }

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {

        actualCategories = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        System.out.println("actualCategories = " + actualCategories);

        // exclude "All" from the list categories
        actualCategories.remove(0);   // index 0  = "All"
        System.out.println("----------After we remove All from the Categories-----------");

        System.out.println("actualCategories = " + actualCategories);

    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        String query = "select name from book_categories";
        DB_Util.runQuery(query);

        List<String> expectedCategories = DB_Util.getColumnDataAsList(1);
        System.out.println("expectedCategories = " + expectedCategories);

        Assert.assertEquals( expectedCategories, actualCategories );

    }


}
