package com.codecool.jiraTest2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Browse {
    protected WebDriver driver;
    protected String url;

    public Browse(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void openPage() {
        driver.navigate().to(url);
    }


    public String getActualKey() {
        return driver.getTitle();
    }
}
