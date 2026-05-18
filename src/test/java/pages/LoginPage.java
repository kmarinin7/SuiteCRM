package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends LoadablePage {

    private final By USERNAME_FIELD = By.id("user_name");
    private final By PASSWORD_FIELD = By.cssSelector("input[type='password']");
    private final By LOGIN_BUTTON = By.name("Login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
        } catch (Exception e) {
            throw new Error("Login page did not load correctly", e);
        }
    }

    @Override
    protected void load() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Users&action=Login");
    }

    public MainPage login(String username, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();

        MainPage mainPage = new MainPage(driver);
        mainPage.get();
        return mainPage;
    }

    public LoginPage open() {
        get();
        return this;
    }
}