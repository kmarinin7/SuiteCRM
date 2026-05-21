package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.AccountDetailPage;
import dto.Account;

@Feature("Account Management")
public class CreateAccountTest extends BaseTest {

    @Test(description = "Проверка создания нового аккаунта")
    @Description("Тест создаёт новый аккаунт через форму Create Account и проверяет, что он отображается на странице деталей")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("ACCOUNT-001")

    public void userCanCreateNewAccount() throws InterruptedException {
        String uniqueAccountName = "Автотест Компания " + System.currentTimeMillis();

        Account testAccount = new Account.Builder()
                .withName(uniqueAccountName)
                .withType("Customer")
                .withPhone("+7 (999) 123-45-67")
                .withWebsite("https://autotest.example.com")
                .build();

        Allure.step("Логин в систему");
        MainPage mainPage = loginPage.login(USERNAME, PASSWORD);

        Allure.step("Создание аккаунта с данными: " + testAccount.getName());
        AccountDetailPage detailPage = mainPage
                .goToCreateAccount()
                .createAccount(testAccount);

        Allure.step("Проверка, что аккаунт создан");
        String actualAccountName = detailPage.getAccountName();

        Allure.step("Сравнение ожидаемого и фактического имени аккаунта");
        Assert.assertEquals(actualAccountName, uniqueAccountName,
                "Аккаунт с именем " + uniqueAccountName + " не был найден на странице");
    }
}