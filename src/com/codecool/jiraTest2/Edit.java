package com.codecool.jiraTest2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class Edit {

    private LocalDate localDate = LocalDate.now();
    public WebDriver driver;
    public String url;

    @FindBy (id=("summary-val"))
    private WebElement updateSummary;

    public Edit(WebDriver driver, String url) {
        this.url = url;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void editType(WebDriver driver, String url, String type){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Login.login(driver);
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary"))).sendKeys("This is an "+ type + "..." + localDate);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue-submit"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary-val")));
    }

    public String  getUpdateSummary() {
        return updateSummary.getText();
    }
}