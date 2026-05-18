package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Dropdown;
import wrappers.Textarea;
import dto.Task;

public class AddTaskPage extends LoadablePage {

    public AddTaskPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SAVE")));
        } catch (Exception e) {
            throw new Error("Add Task page did not load correctly", e);
        }
    }

    @Override
    protected void load() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Tasks&action=EditView&return_module=Tasks&return_action=DetailView");
    }

    public AddTaskPage open() {
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

    public AddTaskPage withSubject(String subject) throws InterruptedException {
        if (subject != null && !subject.isEmpty()) {
            WebElement subjectField = driver.findElement(By.name("name"));
            scrollToElement(subjectField);
            subjectField.clear();
            subjectField.sendKeys(subject);
            System.out.println("Subject заполнен: " + subject);
            Thread.sleep(300);
        }
        return this;
    }

    public AddTaskPage withStatus(String status) throws InterruptedException {
        if (status != null && !status.isEmpty()) {
            new Dropdown(driver, "Status").select(status);
            System.out.println("Status выбран: " + status);
            Thread.sleep(300);
        }
        return this;
    }

    public AddTaskPage withPriority(String priority) throws InterruptedException {
        if (priority != null && !priority.isEmpty()) {
            new Dropdown(driver, "Priority").select(priority);
            System.out.println("Priority выбран: " + priority);
            Thread.sleep(300);
        }
        return this;
    }

    public AddTaskPage withDescription(String description) throws InterruptedException {
        if (description != null && !description.isEmpty()) {
            new Textarea(driver, "description").write(description);
            Thread.sleep(300);
        }
        return this;
    }

    public AddTaskPage withStartDate(String startDate) throws InterruptedException {
        if (startDate != null && !startDate.isEmpty()) {
            try {
                String[] parts = startDate.split("-");
                String formattedDate = parts[1] + "/" + parts[2] + "/" + parts[0];

                WebElement dateField = driver.findElement(By.id("date_start_date"));
                scrollToElement(dateField);
                dateField.clear();
                dateField.sendKeys(formattedDate);

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("if(typeof combo_date_start !== 'undefined') { combo_date_start.update(); }");
                System.out.println("Start Date заполнена: " + formattedDate);
                Thread.sleep(300);
            } catch (Exception e) {
                System.out.println("Не удалось заполнить Start Date: " + e.getMessage());
            }
        }
        return this;
    }

    public AddTaskPage withDateDue(String dateDue) throws InterruptedException {
        if (dateDue != null && !dateDue.isEmpty()) {
            try {
                String[] parts = dateDue.split("-");
                String formattedDate = parts[1] + "/" + parts[2] + "/" + parts[0];

                WebElement dateField = driver.findElement(By.id("date_due_date"));
                scrollToElement(dateField);
                dateField.clear();
                dateField.sendKeys(formattedDate);

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("if(typeof combo_date_due !== 'undefined') { combo_date_due.update(); }");
                System.out.println("Due Date заполнена: " + formattedDate);
                Thread.sleep(300);
            } catch (Exception e) {
                System.out.println("Не удалось заполнить Due Date: " + e.getMessage());
            }
        }
        return this;
    }

    public AddTaskPage withAssignedTo(String assignedTo) throws InterruptedException {
        if (assignedTo != null && !assignedTo.isEmpty()) {
            try {
                WebElement assignedField = driver.findElement(By.id("assigned_user_name"));
                scrollToElement(assignedField);
                assignedField.clear();
                assignedField.sendKeys(assignedTo);
                Thread.sleep(1000);
                assignedField.sendKeys(Keys.ENTER);
                Thread.sleep(500);
                System.out.println("Пользователь выбран: " + assignedTo);
            } catch (Exception e) {
                System.out.println("Не удалось выбрать пользователя: " + e.getMessage());
            }
        }
        return this;
    }

    public AddTaskPage withContactName(String contactName) throws InterruptedException {
        if (contactName != null && !contactName.isEmpty()) {
            try {
                WebElement contactField = driver.findElement(By.id("contact_name"));
                scrollToElement(contactField);
                Thread.sleep(500);
                contactField.clear();
                contactField.sendKeys(contactName);
                System.out.println("Contact Name введён: " + contactName);
                Thread.sleep(1000);

                contactField.sendKeys(Keys.ENTER);
                System.out.println("Контакт выбран через Enter");
                Thread.sleep(500);

            } catch (Exception e) {
                System.out.println("Не удалось заполнить Contact Name: " + e.getMessage());
            }
        }
        return this;
    }

    public TaskDetailPage createTask(Task task) throws InterruptedException {
        return withSubject(task.getSubject())
                .withStatus(task.getStatus())
                .withPriority(task.getPriority())
                .withDescription(task.getDescription())
                .withStartDate(task.getStartDate())
                .withDateDue(task.getDateDue())
                .withAssignedTo(task.getAssignedTo())
                .withContactName(task.getContactName())  // ← ДОБАВЛЕНО
                .saveTask();
    }

    public TaskDetailPage saveTask() {
        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("SAVE")));
            scrollToElement(saveButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
            System.out.println("Кнопка Save нажата");
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении задачи: " + e.getMessage());
        }

        TaskDetailPage detailPage = new TaskDetailPage(driver);
        detailPage.get();
        return detailPage;
    }
}