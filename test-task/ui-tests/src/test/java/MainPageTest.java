import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTest extends BaseTest {
    @Test
    public void mainPage_open_logoIsShown() {
        // act
        MainPage mainPage = new MainPage(getDriver());
        // assert
        Assert.assertTrue(mainPage.mainMenu().isLogoShown());
    }

    @Test
    public void mainPage_navigateToFilms_filmsPageIsShown() {
        // arrange
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        // act
        Page filmsPage = mainPage.mainMenu().navigateTo(MainMenu.MainMenuLinks.FILMS);
        // assert
        Assert.assertTrue(filmsPage.mainMenu().isLogoShown());
    }

    @Test
    public void mainPage_navigateToVideo_videoPageIsShown() {
        // arrange
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        // act
        Page videoPage = mainPage.mainMenu().navigateTo(MainMenu.MainMenuLinks.VIDEO);
        // assert
        Assert.assertTrue(videoPage.mainMenu().isLogoShown());
    }

    @Test
    public void mainPage_openPage_sectionsAreShown() {
        // arrange
        MainPage mainPage = new MainPage(getDriver());
        // act
        mainPage.open();
        // assert
        Assert.assertEquals(mainPage.getSectionsCount(), 4);
        Assert.assertTrue(mainPage.isSectionShown(MainPage.Section.LATEST_NEWS));
        Assert.assertTrue(mainPage.isSectionShown(MainPage.Section.LATEST_VIDEO));
        Assert.assertTrue(mainPage.isSectionShown(MainPage.Section.EXPLORE_MORE));
    }
}