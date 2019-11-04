package com.codecool.jiraTest2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codecool.jiraTest2.Login.login;

public class IssueTypes {

    public boolean verifyIssueTypes(WebDriver driver){
        login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/summary");
        List<String> issueTypeNamesInSetting = new ArrayList<>();
        List<WebElement> issueTypesInSettings = driver.findElements(By.xpath("//*[@id='project-config-webpanel-summary-issuetypes']/div[2]/div/ul/li/span/span/a"));
        for (WebElement issue : issueTypesInSettings) {
            issueTypeNamesInSetting.add(issue.getText());
        }
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        List<String> issueTypeNamesInGlass = new ArrayList<>();
        List<WebElement> issueTypesInGlass = driver.findElements(By.xpath("//*[@id='glass-general-panel']/div[1]/div[1]/div/table/tbody/tr[8]/td[2]/span"));
        for (
                WebElement issue : issueTypesInGlass) {
            issueTypeNamesInGlass.add(issue.getAttribute("title"));
        }
        Collections.sort(issueTypeNamesInGlass);
        Collections.sort(issueTypeNamesInSetting);


        return issueTypeNamesInGlass.equals(issueTypeNamesInSetting);
    }
}
