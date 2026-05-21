package tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.DriverManager;

public class BaseTest {
    protected LoginPage loginPage;
    protected WebDriver driver;

    protected final String USERNAME = "will";
    protected final String PASSWORD = "will";

    @BeforeMethod
    public void setUp() {
        DriverManager.createChromeDriver();
        driver = DriverManager.getDriver();

        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}