import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Type;

public class MainMenu extends BasePage {
    @FindBy(id="nav-sw-logo")
    private WebElement starWarsLogo;

    @FindBy(id="section-links")
    private WebElement siteNavigationMenu;

    MainMenu(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoShown() {
        return this.starWarsLogo.isDisplayed();
    }

    public Page navigateTo(MainMenuLinks mainMenuLinks) {
        String link = String.format("a[data-section=%s]", mainMenuLinks.toString().toLowerCase());
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