package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isAccountInList(String accountName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(String.format("//a[contains(text(), '%s')]", accountName))
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AccountDetailPage clickOnAccount(String accountName) {
        driver.findElement(By.xpath(String.format("//a[contains(text(), '%s')]", accountName))).click();
        return new AccountDetailPage(driver);
    }
}