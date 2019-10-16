import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTest extends BaseTest {
    @Test
    public void mainPage_Open_LogoIsShown() {
        // arrange
        MainPage mainPage = new MainPage(getDriver());
        // act
        mainPage.open();
        // assert
        Assert.assertTrue(mainPage.mainMenu().isLogoShown());
    }

    @Test
    public void mainPage_NavigateToFilms_FilmsPageIsShown() {
        // arrange
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        // act
        Page filmsPage = mainPage.mainMenu().navigateTo(MainMenu.MainMenuLinks.FILMS);
        // assert
        Assert.assertTrue(filmsPage.mainMenu().isLogoShown());
    }

    @Test
    public void mainPage_NavigateToVideo_VideoPageIsShown() {
        // arrange
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        // act
        Page videoPage = mainPage.mainMenu().navigateTo(MainMenu.MainMenuLinks.VIDEO);
        // assert
        Assert.assertTrue(videoPage.mainMenu().isLogoShown());
    }

    @Test
    public void mainPage_OpenPage_SectionsAreShown() {
        // arrange
        MainPage mainPage = new MainPage(getDriver());
        // act
        mainPage.open();
        // assert
        Assert.assertEquals(mainPage.getSectionsCount(), 2);
        Assert.assertTrue(mainPage.isSectionShown(MainPage.Section.LATEST_NEWS));
        Assert.assertTrue(mainPage.isSectionShown(MainPage.Section.LATEST_VIDEO));
    }
}