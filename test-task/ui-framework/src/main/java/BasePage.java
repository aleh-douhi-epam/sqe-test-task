import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    private static final Duration POLLING = Duration.ofMillis(200);

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, (int)TIMEOUT.toSeconds()), this);
    }

    protected WebElement waitForElementToAppear(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementHeightIsGreaterThanSpecified(WebElement webElement, int height) {
        wait.until((driver) -> webElement.getRect().height > height);
    }

    protected boolean waitTillAjaxLoad() {
        try {
            wait.until((driver) -> {
                try {
                    return (boolean)((JavascriptExecutor)driver).executeScript("return (typeof jQuery != 'undefined') && (jQuery.active === 0)");
                }
                catch (Exception e) {
                    return false;
                }
            });
        }
        catch (TimeoutException exception) {
            // If timeout, then AJAX calls are not completed. For all other exceptions, do not catch.
        }
        return false;
    }

    protected boolean waitTillPageLoad() {
        try {
            wait.until((driver) -> {
                try {
                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().contains("complete");
                }
                catch (Exception e) {
                    return false;
                }
            });
        }
        catch (TimeoutException exception)
        {
            // If timeout, then page is not loaded. For all other exceptions, do not catch.
        }
        return false;
    }
}
