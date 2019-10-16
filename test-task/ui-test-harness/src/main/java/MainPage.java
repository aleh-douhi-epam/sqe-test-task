import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage implements Page {
    @FindBy(css="h2.module_title")
    private List<WebElement> moduleTitle;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ToolConfig.getBaseUrl());
    }

    @Override
    public MainMenu mainMenu(){
        return new MainMenu(driver);
    }

    public int getSectionsCount() {
        return moduleTitle.size();
    }

    public boolean isSectionShown(Section section) {
        return moduleTitle.stream().anyMatch(element -> element.getText().toUpperCase().contains(section.toString()));
    }

    public enum Section {
        LATEST_NEWS,
        LATEST_VIDEO;

        @Override
        public String toString() {
            return this.name().replace("_", " ");
        }
    }
}