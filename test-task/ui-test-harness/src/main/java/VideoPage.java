import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VideoPage extends BasePage implements Page {
    @FindBy(css="form.search-form div.query-field>input[name=q]")
    private WebElement searchField;

    @FindBy(css="input[value=Search]")
    private WebElement searchButton;

    @FindBy(css="div.module_header h2")
    private List<WebElement> moduleTitle;

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ToolConfig.getBaseUrl() + "video");
    }

    public SearchPage searchFor(String searchQuery) {
        waitTillPageLoad();
        waitTillAjaxLoad();
        searchField.sendKeys(searchQuery);
        searchButton.click();
        return new SearchPage(driver);
    }

    public int getSectionsCount() {
        return moduleTitle.size();
    }

    public boolean isSectionShown(VideoSection videoSection) {
        return moduleTitle.stream().anyMatch(element -> element.getText().toUpperCase().contains(videoSection.toString()));
    }

    @Override
    public MainMenu mainMenu() {
        return new MainMenu(driver);
    }

    public enum VideoSection {
        MORE,
        FEATURED,
        BROWSE
    }
}
