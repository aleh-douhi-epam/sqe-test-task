import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
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

    // chrome driver supplier
    private static final Supplier<WebDriver> chromeDriverSupplier = () -> {
        ChromeDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };

    // firefox driver supplier
    private static final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        FirefoxDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };

    // ie driver supplier
    private static final Supplier<WebDriver> ieDriverSupplier = () -> {
        InternetExplorerDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    };

    // edge driver supplier
    private static final Supplier<WebDriver> edgeDriverSupplier = () -> {
        EdgeDriverManager.edgedriver().setup();
        return new EdgeDriver();
    };

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