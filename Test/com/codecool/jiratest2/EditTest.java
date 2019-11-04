package com.codecool.jiratest2;

import com.codecool.jiraTest2.Edit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.testng.Assert;

import java.time.LocalDate;

public class EditTest {

    public WebDriver driver;
    public LocalDate localDate = LocalDate.now();

    @BeforeEach
    public void setUp() {
        System.out.println("*******************");
        System.out.println("launching chrome browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "resources/edit.csv")
    public void editTypeTest(String url, String type) {
        Edit edit = new Edit(driver,url);
        edit.editType(driver,url,type);
        Assert.assertEquals(edit.getUpdateSummary(), "This is an "+ type + "..." + localDate);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing chrome browser");
            driver.quit();
        }
    }
}
