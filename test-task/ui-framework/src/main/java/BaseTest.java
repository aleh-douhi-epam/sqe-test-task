import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    private static WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        if (null == driver) {
            driver = DriverFactory.getDriver(DriverType.valueOf(ToolConfig.getBrowser()));
            driver.manage().window().maximize();
        }
    }

    @AfterSuite
    public void afterSuite() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }
}