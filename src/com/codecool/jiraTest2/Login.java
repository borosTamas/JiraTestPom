package com.codecool.jiraTest2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Login {

    public WebDriver driver;

    @FindBy(id="log_out")
    private WebElement logOut;

    @FindBy(xpath="//div[contains(@class, 'aui-message error')]")
    private WebElement message;

    public Login(){

    }

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void login(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username"))).sendKeys(System.getenv("userName"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-password"))).sendKeys(System.getenv("password"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-submit"))).click();
    }


    public void simpleLogin(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        login(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname"))).click();

    }

    public void parameterizedLogin(WebDriver driver,String user, String password){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username"))).sendKeys(user);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-password"))).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-submit"))).click();
    }

    public void logout(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        login(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname"))).click();

    }

    public boolean  getLogOut() {
        return logOut.isDisplayed();
    }

    public boolean getErrorMessage(){
        return message.isDisplayed();
    }


}
