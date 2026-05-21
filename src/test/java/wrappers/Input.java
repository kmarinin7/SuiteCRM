package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Input {
    private WebDriver driver;
    private String label;
    private WebDriverWait wait;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void write(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        try {
            String xpathPattern = "//div[contains(text(), '%s')]/parent::div//input | " +
                    "//label[contains(text(), '%s')]/following-sibling::div//input";

            By locator = By.xpath(String.format(xpathPattern, label, label));

            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Ошибка при вводе в поле '" + label + "': " + e.getMessage());
        }
    }
}