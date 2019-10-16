import org.testng.Assert;
import org.testng.annotations.Test;

public class FilmsPageTest extends BaseTest {
    @Test
    public void filmsPage_OpenPage_SectionsAreShown() {
        // arrange
        FilmsPage filmsPage = new FilmsPage(getDriver());
        // act
        filmsPage.open();
        // assert
        Assert.assertEquals(filmsPage.getFilmsCount(), 11);
    }

    @Test
    public void filmsPage_SelectFilm_FilmPageIsShown() {
        // arrange
        FilmsPage filmsPage = new FilmsPage(getDriver());
        filmsPage.open();
        // act
        filmsPage.selectFilmFromSelector(FilmsPage.Films.THE_PHANTOM_MENACE);
        // assert
        Assert.assertTrue(filmsPage.isFilmLogoShown(FilmsPage.Films.THE_PHANTOM_MENACE));
    }
}