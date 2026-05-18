package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AddTaskPage;

public class MainPage extends LoadablePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("index.php?module=Home"),
                    ExpectedConditions.urlContains("module=Home")
            ));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
        } catch (Exception e) {
            throw new Error("Main page did not load correctly", e);
        }
    }

    @Override
    protected void load() {
    }

    public AddAccountPage goToCreateAccount() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Accounts&action=EditView&return_module=Accounts&return_action=DetailView");
        AddAccountPage addAccountPage = new AddAccountPage(driver);
        addAccountPage.get();
        return addAccountPage;
    }

    public AddContactPage goToCreateContact() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Contacts&action=EditView&return_module=Contacts&return_action=DetailView");
        AddContactPage addContactPage = new AddContactPage(driver);
        addContactPage.get();
        return addContactPage;
    }
    public AddTaskPage goToCreateTask() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Tasks&action=EditView&return_module=Tasks&return_action=DetailView");
        AddTaskPage addTaskPage = new AddTaskPage(driver);
        addTaskPage.get();
        return addTaskPage;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}