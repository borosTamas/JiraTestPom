package com.codecool.jiraTest2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void login(WebDriver driver){

        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(By.id("login-form-username")).sendKeys(System.getenv("userName"));
        driver.findElement(By.id("login-form-password")).sendKeys(System.getenv("password"));
        driver.findElement(By.id("login-form-submit")).click();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }
    public static void main(String[] args) {
        System.out.println(System.getenv("userName"));

    }
}
