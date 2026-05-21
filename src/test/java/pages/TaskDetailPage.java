package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TaskDetailPage extends LoadablePage {

    public TaskDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("DetailView"),
                    ExpectedConditions.urlContains("module=Tasks&action=DetailView")
            ));
        } catch (Exception e) {
            throw new Error("Task detail page did not load correctly", e);
        }
    }

    @Override
    protected void load() {
    }

    public String getTaskSubject() {
        try {
            By locator = By.xpath("//h2[@class='module-title-text']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String fullText = driver.findElement(locator).getText().trim();
            // Убираем лишний текст, оставляем только тему задачи
            if (fullText.contains(" » ")) {
                return fullText.split(" » ")[0];
            }
            return fullText;
        } catch (Exception e) {
            System.out.println("Не удалось получить тему задачи: " + e.getMessage());
            return "";
        }
    }

    public boolean isTaskSubjectCorrect(String expectedSubject) {
        String actual = getTaskSubject().toUpperCase();
        String expected = expectedSubject.toUpperCase();
        boolean result = actual.contains(expected) || expected.contains(actual);
        System.out.println("Ожидалось: " + expected + ", Получено: " + actual + ", Совпадает: " + result);
        return result;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
