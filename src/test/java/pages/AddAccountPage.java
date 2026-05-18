package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Dropdown;
import wrappers.Input;
import dto.Account;

public class AddAccountPage extends LoadablePage {

    public AddAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SAVE")));
        } catch (Exception e) {
            throw new Error("Add Account page did not load correctly", e);
        }
    }

    @Override
    protected void load() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Accounts&action=EditView&return_module=Accounts&return_action=DetailView");
    }

    public AddAccountPage withName(String name) throws InterruptedException {
        if (name != null && !name.isEmpty()) {
            new Input(driver, "Name").write(name);
            Thread.sleep(300);
        }
        return this;
    }

    public AddAccountPage withPhone(String phone) throws InterruptedException {
        if (phone != null && !phone.isEmpty()) {
            new Input(driver, "Office Phone").write(phone);
            Thread.sleep(300);
        }
        return this;
    }

    public AddAccountPage withWebsite(String website) throws InterruptedException {
        if (website != null && !website.isEmpty()) {
            new Input(driver, "Website").write(website);
            Thread.sleep(300);
        }
        return this;
    }

    public AddAccountPage withType(String type) throws InterruptedException {
        if (type != null && !type.isEmpty()) {
            new Dropdown(driver, "Type").select(type);
            Thread.sleep(300);
        }
        return this;
    }

    public AccountDetailPage createAccount(Account account) throws InterruptedException {
        return withName(account.getName())
                .withPhone(account.getPhone())
                .withWebsite(account.getWebsite())
                .withType(account.getType())
                .saveAccount();
    }

    public AccountDetailPage saveAccount() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("SAVE")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", saveButton);
        js.executeScript("arguments[0].click();", saveButton);

        AccountDetailPage detailPage = new AccountDetailPage(driver);
        detailPage.get();
        return detailPage;
    }
}
