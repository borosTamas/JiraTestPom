package com.codecool.jiraTest2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseProject extends Browse {
    private WebElement actualKey;

    public BrowseProject(WebDriver driver, String url) {
        super(driver, url);
    }

    public WebElement getLogo() {
        actualKey = driver.findElement(By.xpath("//a[@class='jira-project-avatar']"));
        return actualKey;
    }
}
