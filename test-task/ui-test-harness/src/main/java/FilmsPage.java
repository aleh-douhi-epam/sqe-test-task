import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FilmsPage extends BasePage implements Page {
    @FindBy(css="h3.title")
    private List<WebElement> moduleTitle;

    @FindBy(css="div.films-content img")
    private WebElement filmLogo;

    @FindBy(css="span.drop-expand")
    private WebElement filmSelectorDropDown;

    @FindBy(css="ul.drop-container")
    private WebElement filmSelectorContainer;

    public FilmsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ToolConfig.getBaseUrl() + "films");
    }

    @Override
    public MainMenu mainMenu() {
        return new MainMenu(driver);
    }

    public int getFilmsCount() {
        return moduleTitle.size();
    }

    public void selectFilmFromSelector(Films film) {
        waitTillAjaxLoad();
        filmSelectorDropDown.click();
        waitForElementHeightIsGreaterThanSpecified(filmSelectorContainer, 260);
        waitForElementToAppear(By.partialLinkText(film.toString())).click();
    }

    public boolean isFilmLogoShown(Films film) {
        waitTillAjaxLoad();
        return filmLogo.getAttribute("alt").toUpperCase().contains(film.toString());
    }

    public enum Films {
        THE_PHANTOM_MENACE,
        ATTACK_OF_THE_CLONES,
        REVENGE_OF_THE_SITH;

        @Override
        public String toString() {
            return this.name().replace("_", " ");
        }
    }
}
