package com.codecool.jiratest2;

import com.codecool.jiraTest2.Component;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ComponentTest {

    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.out.println("*******************");
        System.out.println("launching chrome browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/components.csv")
    public void componentsTest(String url, String xpath, String result){
        Component comp = new Component();
        Assert.assertTrue(comp.componentsWithGlassTestPP1(driver,url,xpath,result));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing chrome browser");
            driver.quit();
        }
    }
}
