import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainMenu extends BasePage {
    @FindBy(id="local-nav-logo-desktop")
    private WebElement starWarsLogo;

    @FindBy(id="local-nav-bar-secondary")
    private WebElement siteNavigationMenu;

    MainMenu(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoShown() {
        return this.starWarsLogo.isDisplayed();
    }

    public Page navigateTo(MainMenuLinks mainMenuLinks) {
        String link = String.format("a[data-title=%s]", mainMenuLinks);
        siteNavigationMenu.findElement(By.cssSelector(link)).click();
        switch (mainMenuLinks) {
            case FILMS:
                return new FilmsPage(driver);
            case VIDEO:
                return new VideoPage(driver);
            default:
                return new MainPage(driver);
        }
    }

    public enum MainMenuLinks {
        VIDEO,
        FILMS,
        SERIES
    }
}