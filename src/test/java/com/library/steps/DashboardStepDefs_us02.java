package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.sql.ResultSet;


public class DashboardStepDefs_us02 {


    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    String actualBorrowedBook;

    //  US02

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {

        loginPage.login(user);
        BrowserUtil.waitFor(3);

    }


    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        // OPT 1 --> WebElement
        actualBorrowedBook = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBook = " + actualBorrowedBook);

        // OPT 2 --> Method
        String opt2 = dashBoardPage.getModuleCount("Borrowed Books");
        System.out.println("getModuleCount = " + opt2);

    }


    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        String query = "select count(*) from book_borrow where is_returned = 0";

        DB_Util.runQuery(query);

        String expectedBorrowBook = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowBook = " + expectedBorrowBook);

        Assert.assertEquals( expectedBorrowBook, actualBorrowedBook );

    }



}
