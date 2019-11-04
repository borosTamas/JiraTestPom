package com.codecool.jiratest2;

import com.codecool.jiraTest2.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainTest {
    public static Main main = new Main();
    public WebDriver driver;
    public LocalDate localDate = LocalDate.now();



    @BeforeClass
    public void setUp() {
        System.out.println("*******************");
        System.out.println("launching chrome browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openJira() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/");
        Assert.assertEquals(driver.getTitle(), "System Dashboard - Jira");
    }

    @Test
    public void loginTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        WebElement user = driver.findElement(By.id("login-form-username"));
        WebElement password = driver.findElement(By.id("login-form-password"));
        WebElement login = driver.findElement(By.id("login-form-submit"));
        user.sendKeys(System.getProperty("userName"));
        password.sendKeys(System.getProperty("password"));
        login.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement userProfile = driver.findElement(By.id("header-details-user-fullname"));
        userProfile.click();
        WebElement logout = driver.findElement(By.id("log_out"));
        Assert.assertTrue(logout.isDisplayed());
    }

    @Test
    public void logOutTest() {
        main.login(driver);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement userProfile = driver.findElement(By.id("header-details-user-fullname"));
        userProfile.click();
        WebElement logout = driver.findElement(By.id("log_out"));
        Assert.assertTrue(logout.isDisplayed());
    }

    @Test
    public void wrongUserTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
        WebElement user = driver.findElement(By.id("login-form-username"));
        WebElement password = driver.findElement(By.id("login-form-password"));
        WebElement login = driver.findElement(By.id("login-form-submit"));
        user.sendKeys("user20l");
        password.sendKeys(System.getProperty("password"));
        login.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(@class, 'aui-message error')]"));
        Assert.assertTrue(errorMessage.isDisplayed());

    }

    @Test
    public void wrongPassTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement user = driver.findElement(By.id("login-form-username"));
        WebElement password = driver.findElement(By.id("login-form-password"));
        WebElement login = driver.findElement(By.id("login-form-submit"));
        user.sendKeys(System.getProperty("userName"));
        password.sendKeys("000");
        login.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(@class, 'aui-message error')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }


    @Test
    public void openIssueTest() {
        main.login(driver);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.get("https://jira.codecool.codecanvas.hu/browse/PP1-38");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        String issueName = driver.findElement(By.id("key-val")).getAttribute("innerHTML");
        Assert.assertEquals(issueName, "PP1-38");
    }

    @Test
    public void allProjects() {
        main.login(driver);
        WebElement projects = driver.findElement(By.id("browse_link"));
        projects.click();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        WebElement allProject = driver.findElement(By.id("project_view_all_link_lnk"));
        allProject.click();
        WebElement mtpKey = driver.findElement(By.xpath("//td[contains(text(), 'MTP')]"));
        Assert.assertTrue(mtpKey.isDisplayed());

    }

    @Test
    public void browseJeti() {
        main.login(driver);
        WebElement projects = driver.findElement(By.id("browse_link"));
        projects.click();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        WebElement allProject = driver.findElement(By.id("project_view_all_link_lnk"));
        allProject.click();
        WebElement searchField = driver.findElement(By.id("project-filter-text"));
        searchField.sendKeys("JETI");
        WebElement key = driver.findElement(By.xpath("//td[contains(text(), 'JETI')]"));
        Assert.assertTrue(key.isDisplayed());

    }

    @Test
    public void browseToucan() {
        main.login(driver);
        WebElement projects = driver.findElement(By.id("browse_link"));
        projects.click();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        WebElement allProject = driver.findElement(By.id("project_view_all_link_lnk"));
        allProject.click();
        WebElement searchField = driver.findElement(By.id("project-filter-text"));
        searchField.sendKeys("TOUCAN");
        WebElement key = driver.findElement(By.xpath("//td[contains(text(), 'TOUCAN')]"));
        Assert.assertTrue(key.isDisplayed());

    }

    @Test
    public void browseCoala() {
        main.login(driver);
        WebElement projects = driver.findElement(By.id("browse_link"));
        projects.click();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        WebElement allProject = driver.findElement(By.id("project_view_all_link_lnk"));
        allProject.click();
        WebElement searchField = driver.findElement(By.id("project-filter-text"));
        searchField.sendKeys("COALA");
        WebElement key = driver.findElement(By.xpath("//td[contains(text(), 'COALA')]"));
        Assert.assertTrue(key.isDisplayed());

    }


    @Test
    public void editEpicTypeTest() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/MTP-160");
        WebElement editButton = driver.findElement(By.id("edit-issue"));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        editButton.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement summary = driver.findElement(By.id("summary"));
        summary.clear();
        summary.sendKeys("This is an Epic..." + localDate);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement update = driver.findElement(By.id("edit-issue-submit"));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        update.click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        WebElement updateSummary = driver.findElement(By.id("summary-val"));
        Assert.assertEquals(updateSummary.getText(), "This is an Epic..." + localDate);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test
    public void editTaskTypeTest() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/MTP-158");
        WebElement editButton = driver.findElement(By.id("edit-issue"));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        editButton.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement summary = driver.findElement(By.id("summary"));
        summary.clear();
        summary.sendKeys("This is an Task..." + localDate);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement update = driver.findElement(By.id("edit-issue-submit"));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        update.click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        WebElement updateSummary = driver.findElement(By.id("summary-val"));
        Assert.assertEquals(updateSummary.getText(), "This is an Task..." + localDate);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test
    public void editImprovementTypeTest() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/MTP-157");
        WebElement editButton = driver.findElement(By.id("edit-issue"));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        editButton.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement summary = driver.findElement(By.id("summary"));
        summary.clear();
        summary.sendKeys("This is an Improvement..." + localDate);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement update = driver.findElement(By.id("edit-issue-submit"));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        update.click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        WebElement updateSummary = driver.findElement(By.id("summary-val"));
        Assert.assertEquals(updateSummary.getText(), "This is an Improvement..." + localDate);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test
    public void editNewFeatureTypeTest() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/MTP-159");
        WebElement editButton = driver.findElement(By.id("edit-issue"));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        editButton.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement summary = driver.findElement(By.id("summary"));
        summary.clear();
        summary.sendKeys("This is an New Feature..." + localDate);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement update = driver.findElement(By.id("edit-issue-submit"));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        update.click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        WebElement updateSummary = driver.findElement(By.id("summary-val"));
        Assert.assertEquals(updateSummary.getText(), "This is an New Feature..." + localDate);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test
    public void browseIssueTest() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/MTP-1");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "MTP-1");
    }

    @Test
    public void browseCoala1Test() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/COALA-1");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "COALA-1");
    }

    @Test
    public void browseCoala2Test() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/COALA-2");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "COALA-2");
    }

    @Test
    public void browseCoala3Test() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/COALA-3");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "COALA-3");
    }

    @Test
    public void browseToucan1Test() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/TOUCAN-1");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "TOUCAN-1");
    }

    @Test
    public void browseToucan2Test() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/TOUCAN-2");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "TOUCAN-2");
    }

    @Test
    public void browseToucan3Test() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/TOUCAN-3");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "TOUCAN-3");
    }

    @Test
    public void browseJeti1Test() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-1");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "JETI-1");
    }

    @Test
    public void browseJeti2Test() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-2");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "JETI-2");
    }

    @Test
    public void browseJeti3Test() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-3");
        WebElement key = driver.findElement(By.id("key-val"));
        Assert.assertEquals(key.getText(), "JETI-3");
    }

    @Test
    public void componentsWithGlassTestPP1() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        WebElement component = driver.findElement(By.xpath("//*[@id='components-table']/tbody[2]/tr[11]/td[1]/div/a"));
        component.click();
        String parentWindow = driver.getWindowHandle();
        Set<String> allwindows = driver.getWindowHandles();
        for (String childWindow : allwindows) {
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
            }
        }
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        WebElement componentName = driver.findElement(By.xpath("//*[@id='fieldcomponent']"));
        Assert.assertEquals(componentName.getText(), "VeryComponent");
    }

    @Test
    public void componentsWithGlassTestPP3() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP3?selectedItem=com.codecanvas.glass:glass");
        WebElement component = driver.findElement(By.xpath("//*[@id='components-table']/tbody[2]/tr[7]/td[1]/div/a"));
        System.out.println(component.getText());
        Assert.assertTrue(component.isDisplayed());
    }

    @Test
    public void componentsWithGlassTestPP4() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP4?selectedItem=com.codecanvas.glass:glass");
        WebElement component = driver.findElement(By.xpath("//*[@id='components-table']/tbody[2]/tr/td[1]/div/a"));
        Assert.assertTrue(component.isDisplayed());
    }

    @Test
    public void componentsWithGlassTestPP2() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP2?selectedItem=com.codecanvas.glass:glass");
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        WebElement noComponent = driver.findElement(By.id("no-components-container"));
        Assert.assertTrue(noComponent.isDisplayed());
    }

    @Test
    public void permissionTest() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        WebElement navPermission = driver.findElement(By.xpath("//*[@id='glass-permissions-nav']/a"));
        navPermission.click();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        boolean browseIssueAnyUserGlass = driver.findElement(By.xpath("//*[@id='glass-permissions-panel']/div/table/tbody/tr[5]/td[3]/div")).isDisplayed();
        boolean createIssueAnyUserGlass = driver.findElement(By.xpath("//*[@id='glass-permissions-panel']/div/table/tbody/tr[8]/td[3]/div")).isDisplayed();
        boolean editIssueAnyUserGlass = driver.findElement(By.xpath("//*[@id='glass-permissions-panel']/div/table/tbody/tr[18]/td[3]/div")).isDisplayed();
        driver.get("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/permissions");
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        boolean browseIssueAnyUserSetting = driver.findElement(By.xpath("//*[@id='project-config-panel-permissions']/jira-permissions-table/div[1]/table/tbody/tr[2]/td[2]/dl/dd")).getText().equals("Any logged in user");
        boolean createIssueAnyUserSetting = driver.findElement(By.xpath("//*[@id='project-config-panel-permissions']/jira-permissions-table/div[2]/table/tbody/tr[3]/td[2]/dl/dd")).getText().equals("Any logged in user");
        boolean editIssueAnyUserSetting = driver.findElement(By.xpath("//*[@id='project-config-panel-permissions']/jira-permissions-table/div[2]/table/tbody/tr[6]/td[2]/dl/dd")).getText().equals("Any logged in user");
        Assert.assertEquals(browseIssueAnyUserGlass, browseIssueAnyUserSetting);
        Assert.assertEquals(createIssueAnyUserGlass, createIssueAnyUserSetting);
        Assert.assertEquals(editIssueAnyUserGlass, editIssueAnyUserSetting);
    }

    @Test
    public void verifyIssueTypes() {
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/summary");
        List<String> issueTypeNamesInSetting = new ArrayList<>();
        List<WebElement> issueTypesInSettings = driver.findElements(By.xpath("//*[@id='project-config-webpanel-summary-issuetypes']/div[2]/div/ul/li/span/span/a"));
        for (WebElement issue : issueTypesInSettings) {
            issueTypeNamesInSetting.add(issue.getText());
            System.out.println(issue.getText());
        }
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        List<String> issueTypeNamesInGlass = new ArrayList<>();
        List<WebElement> issueTypesInGlass = driver.findElements(By.xpath("//*[@id='glass-general-panel']/div[1]/div[1]/div/table/tbody/tr[8]/td[2]/span"));
        for (
                WebElement issue : issueTypesInGlass) {
            issueTypeNamesInGlass.add(issue.getAttribute("title"));
            System.out.println(issue.getAttribute("title"));
        }
        Collections.sort(issueTypeNamesInGlass);
        Collections.sort(issueTypeNamesInSetting);
        Assert.assertTrue(issueTypeNamesInGlass.equals(issueTypeNamesInSetting));

    }
    @Test
    public void counter(){
        driver.navigate().to("https://ctf.pwmarcz.pl/pin/");
        for (int i = 740; i < 1000; i++) {
            WebElement pass = driver.findElement(By.xpath("/html/body/div/form/div/label/input"));
            pass.clear();
            pass.sendKeys(String.valueOf(i));
            WebElement submit = driver.findElement(By.xpath("/html/body/div/form/button"));
            submit.click();
        }
    }

    private void createIssue(String projectName, String projectIssue, String summaryText){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        main.login(driver);
        WebElement create = driver.findElement(By.id("create_link"));
        create.click();
        WebElement project = driver.findElement(By.id("project-field"));
        project.click();
        project.sendKeys(projectName+ Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issuetype-field")));
        WebElement issue = driver.findElement(By.id("issuetype-field"));
        issue.click();
        issue.sendKeys(projectIssue+Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary")));
        WebElement summary = driver.findElement(By.id("summary"));
        summary.sendKeys(summaryText);
        WebElement submit = driver.findElement(By.id("create-issue-submit"));
        submit.click();
        WebElement check = driver.findElement(By.cssSelector(".issue-created-key"));
        check.click();
        Assert.assertEquals(driver.findElement(By.id("summary-val")).getText(),summaryText);
        WebElement more = driver.findElement(By.id("opsbar-operations_more"));
        more.click();
        WebElement delete = driver.findElement(By.cssSelector("#delete-issue .trigger-label"));
        delete.click();
        WebElement submitDelete = driver.findElement(By.id("delete-issue-submit"));
        submitDelete.click();
        WebElement message = driver.findElement(By.cssSelector(".aui-message"));
        Assert.assertTrue(message.isDisplayed());

    }

    @Test
    public void createJetiIssue1(){ createIssue("JETI","Story","testissue"); }

    @Test
    public void createJetiIssue2(){
        createIssue("JETI","Task","testissue");
    }

    @Test
    public void createJetiIssue3(){
        createIssue("JETI","Bug","testissue");
    }

    @Test
    public void createCoalaIssue1(){
        createIssue("COALA","Story","testissue");
    }

    @Test
    public void createCoalaIssue2(){
        createIssue("COALA","Bug","testissue");
    }

    @Test
    public void createCoalaIssue3(){
        createIssue("COALA","Task","testissue");
    }

    @Test
    public void createToucanIssue1(){
        createIssue("TOUCAN","Task","test");
    }

    @Test
    public void createToucanIssue2(){
        createIssue("TOUCAN","Story","test");
    }

    @Test
    public void createToucanIssue3(){
        createIssue("TOUCAN","Bug","test");
    }

    private void createSubtask(String project, String summaryText){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        main.login(driver);
        driver.get("https://jira.codecool.codecanvas.hu/browse/"+project);
        WebElement dropdown = driver.findElement(By.id("opsbar-operations_more"));
        dropdown.click();
        WebElement subtask = driver.findElement(By.cssSelector("#create-subtask .trigger-label"));
        subtask.click();
        WebElement summary = driver.findElement(By.id("summary"));
        summary.sendKeys(summaryText);
        WebElement submit = driver.findElement(By.id("create-issue-submit"));
        submit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(summaryText)));
        WebElement findSubtask = driver.findElement(By.linkText(summaryText));
        findSubtask.click();
        Assert.assertEquals(driver.findElement(By.id("summary-val")).getText(),summaryText);
        WebElement more = driver.findElement(By.cssSelector(".dropdown-text:nth-child(1)"));
        more.click();
        WebElement delete = driver.findElement(By.linkText("Delete"));
        delete.click();
        WebElement submitDelete = driver.findElement(By.id("delete-issue-submit"));
        submitDelete.click();
        WebElement message = driver.findElement(By.cssSelector(".aui-message"));
        Assert.assertTrue(message.isDisplayed());
    }

    @Test
    public void createSubtaskJeti(){
        createSubtask("JETI-195","test-1");
    }

    @Test
    public void createSubtaskToucan(){
        createSubtask("TOUCAN-4","test-2");
    }

    @Test
    public void createSubtaskCoala(){
        createSubtask("COALA-185","test-3");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing chrome browser");
            driver.quit();
        }
    }
}