import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ToolConfig {
    private static String baseUrl;
    private static String browser;

    static {
        Properties prop = getProperties();
        if (null != prop) {
            baseUrl = prop.getProperty("ui.baseUrl");
            browser = prop.getProperty("ui.browser");
        }
    }

    private static Properties getProperties() {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    static String getBrowser() {
        return browser.toUpperCase();
    }
}
