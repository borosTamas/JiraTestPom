package com.codecool.jiratest2;

import com.codecool.jiraTest2.IssueTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class IssueTypesTest {

    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.out.println("*******************");
        System.out.println("launching chrome browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyIssueTypeTest(){
        IssueTypes issue = new IssueTypes();
        Assert.assertTrue(issue.verifyIssueTypes(driver));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing chrome browser");
            driver.quit();
        }
    }
}
