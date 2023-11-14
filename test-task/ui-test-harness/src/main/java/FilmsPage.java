import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilmsPage extends BasePage implements Page {
    @FindBy(css="h3.title")
    private List<WebElement> moduleTitle;

    @FindBy(css="div.poster img")
    private WebElement filmLogo;

    @FindBy(css="h3.links-list-title")
    private WebElement filmSelectorDropDown;

    @FindBy(css="ul.drop-container")
    private WebElement filmSelectorContainer;

    public FilmsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ToolConfig.getBaseUrl() + MainMenu.MainMenuLinks.FILMS.toString().toLowerCase());
    }

    @Override
    public MainMenu mainMenu() {
        return new MainMenu(driver);
    }

    public int getFilmsCount() {
        return moduleTitle.size();
    }

    public void selectFilmFromDropdown(Films film) {
        waitTillAjaxLoad();
        filmSelectorDropDown.click();
        waitForElementHeightIsGreaterThanSpecified(filmSelectorContainer, 260);
        waitForElementToAppear(By.partialLinkText(film.toString())).click();
    }

    public void selectFilmFromPage(Films film) {
        waitTillAjaxLoad();
        driver.findElement(By.partialLinkText(film.label)).click();
    }

    public boolean isFilmPosterShown(Films film) {
        waitTillAjaxLoad();
        return filmLogo.getAttribute("alt").contains(film.label);
    }

    public enum Films {
        THE_PHANTOM_MENACE("The Phantom Menace"),
        ATTACK_OF_THE_CLONES("Attack of the Clones"),
        REVENGE_OF_THE_SITH("Revenge of the Sith");

        public final String label;

        Films(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.name().replace("_", " ");
        }
    }
}
