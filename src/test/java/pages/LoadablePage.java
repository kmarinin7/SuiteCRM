package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class LoadablePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public LoadablePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected abstract void isLoaded() throws Error;

    protected abstract void load();

    public final void get() {
        load();
        isLoaded();
    }

    protected void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
