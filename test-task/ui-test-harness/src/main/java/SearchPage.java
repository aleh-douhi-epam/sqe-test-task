import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage implements Page {

    @FindBy(css="div.search-view li.video.result")
    private List<WebElement> searchResult;

    SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ToolConfig.getBaseUrl() + "search");
    }

    @Override
    public MainMenu mainMenu() {
        return new MainMenu(driver);
    }

    public int getSearchResultsCount() {
        return searchResult.size();
    }
}