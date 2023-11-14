import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

class DriverFactory {
    private static final Map<DriverType, Supplier<WebDriver>> driverMap = new HashMap<>();

    private static final Supplier<WebDriver> chromeDriverSupplier = ChromeDriver::new;
    private static final Supplier<WebDriver> firefoxDriverSupplier = FirefoxDriver::new;
    private static final Supplier<WebDriver> ieDriverSupplier = InternetExplorerDriver::new;
    private static final Supplier<WebDriver> edgeDriverSupplier = EdgeDriver::new;

    //add all the drivers into a map
    static {
        driverMap.put(DriverType.CHROME, chromeDriverSupplier);
        driverMap.put(DriverType.FIREFOX, firefoxDriverSupplier);
        driverMap.put(DriverType.IE, ieDriverSupplier);
        driverMap.put(DriverType.EDGE, edgeDriverSupplier);
    }

    //return a new driver from the map
    static final WebDriver getDriver(DriverType type) {
        return driverMap.get(type).get();
    }
}