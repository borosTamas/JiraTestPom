package com.codecool.jiratest2;

import com.codecool.jiraTest2.Login;
import com.codecool.jiraTest2.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    public Login login = new Login();
    public WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("*******************");
        System.out.println("launching chrome browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void simpleLoginTest() {
        login.simpleLogin(driver);
        Login log = new Login(driver);
        Assert.assertTrue(log.getLogOut());
    }

    @Test
    public void logoutTest(){
        login.logout(driver);
        Login log = new Login(driver);
        Assert.assertTrue(log.getLogOut());
    }

    @Test
    public void wrongUserTest(){
        login.parameterizedLogin(driver,"user20l",System.getProperty("password"));
        Login log = new Login(driver);
        Assert.assertTrue(log.getErrorMessage());
    }

    @Test
    public void wrongPasswordTest(){
        login.parameterizedLogin(driver,"user5","000");
        Login log = new Login(driver);
        Assert.assertTrue(log.getErrorMessage());

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing chrome browser");
            driver.quit();
        }
    }
}
