package com.codecool.jiraTest2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.codecool.jiraTest2.Login.login;

public class Component {

    public boolean componentsWithGlassTestPP1(WebDriver driver, String url, String xpath, String compName) {
        login(driver);
        driver.get(url);
        WebElement component = driver.findElement(By.linkText(xpath));
        component.click();
        String parentWindow = driver.getWindowHandle();
        Set<String> allwindows = driver.getWindowHandles();
        for (String childWindow : allwindows) {
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
            }
        }
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        WebElement componentName = driver.findElement(By.linkText(compName));

        return componentName.getText().equals(compName);


        //Assert.assertEquals(componentName.getText(), "VeryComponent");
    }
}
