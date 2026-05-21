package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountDetailPage extends LoadablePage {

    public AccountDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            wait.until(ExpectedConditions.urlContains("action=DetailView"));
            wait.until(ExpectedConditions.urlContains("module=Accounts"));
        } catch (Exception e) {
            throw new Error("Account Detail page did not load correctly", e);
        }
    }

    @Override
    protected void load() {
    }

    public boolean isAccountDisplayed(String accountName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(String.format("//*[contains(text(), '%s')]", accountName))
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAccountName() {
        try {
            By locator = By.cssSelector("span.sugar_field");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}