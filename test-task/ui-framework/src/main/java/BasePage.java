import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {
    private static final int TIMEOUT = 30;
    private static final int POLLING = 100;

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected WebElement waitForElementToAppear(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementToAppear(WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForTextToDisappear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
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

    protected void pause(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
