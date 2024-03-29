package com.library.steps;

import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {


    @Before ("@ui")
    public void setUp(){
        System.out.println("this is coming from BEFORE - UI start connection");
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigurationReader.getProperty("library_url"));


    }

    @After ("@ui")
    public void tearDown(Scenario scenario){
        System.out.println("this is coming from AFTER - UI close connection");

        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

        Driver.closeDriver();

    }


    @Before ("@db")
    public void setupDB(){
        System.out.println("Connecting to database...");
        DB_Util.createConnection();
    }

    @After ("@db")
    public void closeDB(){
        System.out.println("Closing DB connection");
    }




}
