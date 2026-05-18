package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Dropdown {
    WebDriver driver;
    String label;
    WebDriverWait wait;

    public Dropdown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void select(String option) {
        try {
            String selectPattern = "//*[contains(text(), '%s') and contains(@class,'label')]/following-sibling::div//select";
            WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(String.format(selectPattern, label))
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectElement);
            Thread.sleep(300);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectElement);
            Thread.sleep(300);

            WebElement optionElement = driver.findElement(By.xpath(
                    String.format(selectPattern + "//option[contains(text(), '%s')]", label, option)
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionElement);
            Thread.sleep(300);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
            Thread.sleep(300);

            System.out.println("Выбрана опция: " + option);
        } catch (Exception e) {
            System.out.println("Ошибка при выборе опции '" + option + "': " + e.getMessage());
        }
    }
}
