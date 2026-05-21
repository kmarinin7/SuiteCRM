package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Input;
import dto.Contact;

public class AddContactPage extends LoadablePage {

    public AddContactPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SAVE")));
        } catch (Exception e) {
            throw new Error("Add Contact page did not load correctly", e);
        }
    }

    @Override
    protected void load() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Contacts&action=EditView&return_module=Contacts&return_action=DetailView");
    }

    public AddContactPage open() {
        get();
        return this;
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AddContactPage withFirstName(String firstName) throws InterruptedException {
        if (firstName != null && !firstName.isEmpty()) {
            WebElement firstNameField = driver.findElement(By.name("first_name"));
            scrollToElement(firstNameField);
            firstNameField.clear();
            firstNameField.sendKeys(firstName);
            System.out.println("First Name заполнен: " + firstName);
            Thread.sleep(300);
        }
        return this;
    }

    public AddContactPage withLastName(String lastName) throws InterruptedException {
        if (lastName != null && !lastName.isEmpty()) {
            WebElement lastNameField = driver.findElement(By.name("last_name"));
            scrollToElement(lastNameField);
            lastNameField.clear();
            lastNameField.sendKeys(lastName);
            System.out.println("Last Name заполнен: " + lastName);
            Thread.sleep(300);
        }
        return this;
    }

    public AddContactPage withAccountName(String accountName) throws InterruptedException {
        if (accountName != null && !accountName.isEmpty()) {
            WebElement accountField = driver.findElement(By.name("account_name"));
            scrollToElement(accountField);
            accountField.clear();
            accountField.sendKeys(accountName);
            Thread.sleep(1500);
            accountField.sendKeys(Keys.ENTER);
            System.out.println("Аккаунт выбран: " + accountName);
            Thread.sleep(500);
        }
        return this;
    }

    public AddContactPage withPhone(String phone) throws InterruptedException {
        if (phone != null && !phone.isEmpty()) {
            WebElement phoneField = driver.findElement(By.name("phone_work"));
            scrollToElement(phoneField);
            phoneField.clear();
            phoneField.sendKeys(phone);
            System.out.println("Phone заполнен: " + phone);
            Thread.sleep(300);
        }
        return this;
    }

    public AddContactPage withEmail(String email) throws InterruptedException {
        if (email != null && !email.isEmpty()) {
            new Input(driver, "Email Address").write(email);
            System.out.println("Email заполнен: " + email);
            Thread.sleep(300);
        }
        return this;
    }

    public AddContactPage fillContact(Contact contact) throws InterruptedException {
        return withFirstName(contact.getFirstName())
                .withLastName(contact.getLastName())
                .withAccountName(contact.getAccountName())
                .withPhone(contact.getPhone())
                .withEmail(contact.getEmail());
    }

    public ContactDetailPage save() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("SAVE")));
        scrollToElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
        System.out.println("Кнопка Save нажата");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ContactDetailPage detailPage = new ContactDetailPage(driver);
        detailPage.get();
        return detailPage;
    }
}