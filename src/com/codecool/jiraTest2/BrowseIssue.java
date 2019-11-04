package com.codecool.jiraTest2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrowseIssue extends Browse {
    @FindBy(id = "key-val")
    private WebElement actualKey;

    public BrowseIssue(WebDriver driver, String url) {
        super(driver, url);
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getActualKey() {

        try
        {
            return actualKey.getText();
        }
        catch (Exception e)
        {
            return "Permission error";
        }

    }
}
