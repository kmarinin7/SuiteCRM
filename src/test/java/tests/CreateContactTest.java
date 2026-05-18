package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import pages.AccountDetailPage;
import pages.AddContactPage;
import pages.ContactDetailPage;
import dto.Account;
import dto.Contact;

@Feature("Contact Management")
public class CreateContactTest extends BaseTest {

    @Test(description = "Проверка создания нового контакта")
    @Description("Тест создаёт аккаунт, затем контакт, привязанный к этому аккаунту")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("CONTACT-001")

    public void userCanCreateNewContact() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Логин в систему");
        MainPage mainPage = loginPage.login(USERNAME, PASSWORD);

        String uniqueAccountName = "Аккаунт для контакта " + System.currentTimeMillis();

        Account testAccount = new Account.Builder()
                .withName(uniqueAccountName)
                .withType("Customer")
                .withPhone("+7 (999) 111-22-33")
                .build();

        Allure.step("Создание аккаунта: " + uniqueAccountName);
        AccountDetailPage accountDetailPage = mainPage
                .goToCreateAccount()
                .createAccount(testAccount);

        String createdAccountName = accountDetailPage.getAccountName();
        softAssert.assertEquals(createdAccountName, uniqueAccountName, "Аккаунт не создался");

        Allure.step("Возврат на главную страницу");
        driver.get("https://demo.suiteondemand.com/index.php?module=Home&action=index");
        Thread.sleep(3000);

        String uniqueContactFirstName = "Автотест";
        String uniqueContactLastName = "Контакт " + System.currentTimeMillis();

        Contact testContact = new Contact.Builder()
                .withFirstName(uniqueContactFirstName)
                .withLastName(uniqueContactLastName)
                .withAccountName(uniqueAccountName)
                .withPhone("+7 (999) 123-45-67")
                .withEmail("autotest@example.com")
                .build();

        Allure.step("Создание контакта: " + uniqueContactFirstName + " " + uniqueContactLastName);
        AddContactPage addContactPage = new AddContactPage(driver);
        addContactPage.open();
        Thread.sleep(1000);

        addContactPage.fillContact(testContact);
        ContactDetailPage contactDetailPage = addContactPage.save();

        Allure.step("Проверка, что контакт создан");
        Thread.sleep(2000);
        boolean isContactCreated = contactDetailPage.isContactNameCorrect(
                uniqueContactFirstName,
                uniqueContactLastName
        );
        softAssert.assertTrue(isContactCreated, "Контакт не был найден на странице");

        softAssert.assertAll();
    }
}