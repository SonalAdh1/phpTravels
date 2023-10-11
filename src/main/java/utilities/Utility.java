package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utility {
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(Utility.class);

    public Utility(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitforvisibility(WebElement path) {
        try {
            wait.until(ExpectedConditions.visibilityOf(path));
            log.info("Element is visible");
        } catch (Exception e) {
            log.error("Element is not visible");
        }
    }
    public void waitforclick(WebElement path) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(path));
            log.info("Element is visible");
        } catch (Exception e) {
            log.error("Element is not visible");
        }
    }

    public void loginfo(String infos) {
        log.info(infos);
    }

    public void logerror(String error) {
        log.error(error);
    }
}
