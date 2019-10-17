import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ToolConfig {
    private static String baseUrl;
    private static String browser;

    static {
        Properties prop = getProperties();
        if (prop != null) {
            baseUrl = prop.getProperty("ui.baseUrl");
            browser = prop.getProperty("ui.browser");
        }
    }

    private static Properties getProperties() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    static String getBrowser() {
        return browser.toUpperCase();
    }
}
