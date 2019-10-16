import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTest extends BaseTest {
    @Test
    public void mainPage_Open_LogoIsShown() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        Assert.assertTrue(mainPage.mainMenu().isLogoShown());
    }

    @Test
    public void mainPage_NavigateToFilms_FilmsPageIsShown() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        Page filmsPage = mainPage.mainMenu().navigateTo(MainMenu.MainMenuLinks.FILMS);
        Assert.assertTrue(filmsPage.mainMenu().isLogoShown());
    }

    @Test
    public void mainPage_NavigateToVideo_VideoPageIsShown() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        Page videoPage = mainPage.mainMenu().navigateTo(MainMenu.MainMenuLinks.VIDEO);
        Assert.assertTrue(videoPage.mainMenu().isLogoShown());
    }

    @Test
    public void mainPage_OpenPage_SectionsAreShown() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        Assert.assertEquals(mainPage.getSectionsCount(), 2);
        Assert.assertTrue(mainPage.isSectionShown(MainPage.Section.LATEST_NEWS));
        Assert.assertTrue(mainPage.isSectionShown(MainPage.Section.LATEST_VIDEO));
    }
}