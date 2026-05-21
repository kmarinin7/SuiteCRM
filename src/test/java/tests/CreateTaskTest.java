package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import pages.TaskDetailPage;
import dto.Task;

@Feature("Task Management")
public class CreateTaskTest extends BaseTest {

    @Test(description = "Проверка создания новой задачи")
    @Description("Тест создаёт новую задачу с заполнением всех полей")
    @Severity(SeverityLevel.NORMAL)
    @Issue("TASK-001")

    public void userCanCreateNewTask() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Логин в систему");
        MainPage mainPage = loginPage.login(USERNAME, PASSWORD);
        Thread.sleep(2000);

        String uniqueSubject = "Автотест Задача " + System.currentTimeMillis();
        String contactName = "Roberto Linville";

        Task testTask = new Task.Builder()
                .withSubject(uniqueSubject)
                .withStatus("Not Started")
                .withPriority("Medium")
                .withDescription("Это автоматический тест создания задачи")
                .withStartDate("2025-12-30")
                .withDateDue("2025-12-31")
                .withAssignedTo("will")
                .withContactName(contactName)
                .build();

        Allure.step("Создание задачи с данными: " + uniqueSubject);
        TaskDetailPage detailPage = mainPage
                .goToCreateTask()
                .createTask(testTask);

        Allure.step("Проверка, что задача создана");
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("DetailView"),
                "Должна быть страница DetailView. Текущий URL: " + currentUrl);
        softAssert.assertTrue(currentUrl.contains("record="),
                "URL должен содержать ID записи");

        boolean isTaskCreated = detailPage.isTaskSubjectCorrect(uniqueSubject);
        softAssert.assertTrue(isTaskCreated,
                "Задача не была найдена на странице. Ожидалось: " + uniqueSubject);

        softAssert.assertAll();
    }
}