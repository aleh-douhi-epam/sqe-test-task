import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BaseTest {
    private static WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        if (driver == null) {
            driver = DriverFactory.getDriver(DriverType.valueOf(ToolConfig.getBrowser()));
            driver.manage().window().maximize();
        }
    }

    @AfterSuite
    public void afterSuite() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }
}