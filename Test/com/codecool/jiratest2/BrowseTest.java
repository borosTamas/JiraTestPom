package com.codecool.jiratest2;

import com.codecool.jiraTest2.BrowseIssue;
import com.codecool.jiraTest2.BrowseProject;
import com.codecool.jiraTest2.Login;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class BrowseTest {
    private WebDriver driver;
    private Login login = new Login();

    @BeforeEach
    public void setUp() {
        System.out.println("*******************");
        System.out.println("launching chrome browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/browseIssueTest.csv", numLinesToSkip = 1)
    public void browseIssues(String issueLink, String exceptedKey){
        login.simpleLogin(driver);
        BrowseIssue issue = new BrowseIssue(driver,issueLink);
        issue.openPage();
        boolean result = issue.getActualKey().equals(exceptedKey);
        Assert.assertTrue(result,"perrrrrmison");
    }
    @ParameterizedTest
    @CsvFileSource(resources = "resources/browseProjectTest.csv", numLinesToSkip = 1)
    public void browseProject(String projectLink){
        login.simpleLogin(driver);
        BrowseProject project = new BrowseProject(driver,projectLink);
        project.openPage();
        Assert.assertTrue(project.getLogo().isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing chrome browser");
            driver.quit();
        }
    }
}
