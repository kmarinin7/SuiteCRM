package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Textarea {
    private WebDriver driver;
    private String name;
    private WebDriverWait wait;

    public Textarea(WebDriver driver, String name) {
        this.driver = driver;
        this.name = name;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void write(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        try {
            By locator = By.xpath(String.format("//textarea[@name='%s']", name));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            wait.until(ExpectedConditions.elementToBeClickable(element));

            element.clear();
            element.sendKeys(text);

            System.out.println("Textarea '" + name + "' заполнен: " + text);
        } catch (Exception e) {
            System.out.println("Не удалось заполнить Textarea '" + name + "': " + e.getMessage());
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(String.format("document.querySelector('textarea[name=\"%s\"]').value = arguments[0];", name), text);
                System.out.println("Textarea '" + name + "' заполнен через JavaScript");
            } catch (Exception ex) {
                System.out.println("Не удалось заполнить Textarea '" + name + "' даже через JavaScript");
            }
        }
    }
}