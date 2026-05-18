package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactDetailPage extends LoadablePage {

    public ContactDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("DetailView"),
                    ExpectedConditions.urlContains("module=Contacts&action=DetailView")
            ));
        } catch (Exception e) {
            throw new Error("Contact detail page did not load correctly", e);
        }
    }

    @Override
    protected void load() {
    }

    public String getFullName() {
        try {
            By locator = By.xpath("//h2[@class='module-title-text']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String fullText = driver.findElement(locator).getText().trim();
            if (fullText.contains(" » ")) {
                return fullText.split(" » ")[0];
            }
            return fullText;
        } catch (Exception e) {
            System.out.println("Не удалось получить имя контакта: " + e.getMessage());
            return "";
        }
    }

    public boolean isContactNameCorrect(String firstName, String lastName) {
        String expected = (firstName + " " + lastName).toUpperCase();
        String actual = getFullName().toUpperCase();
        boolean result = actual.contains(expected) || expected.contains(actual);
        System.out.println("Ожидалось: " + expected + ", Получено: " + actual + ", Совпадает: " + result);
        return result;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}