import org.testng.Assert;
import org.testng.annotations.Test;

public class FilmsPageTest extends BaseTest {
    @Test
    public void filmsPage_OpenPage_SectionsAreShown() {
        FilmsPage filmsPage = new FilmsPage(getDriver());
        filmsPage.open();
        Assert.assertEquals(filmsPage.getFilmsCount(), 11);
    }

    @Test
    public void filmsPage_SelectFilm_FilmPageIsShown() {
        FilmsPage filmsPage = new FilmsPage(getDriver());
        filmsPage.open();
        filmsPage.selectFilmFromSelector(FilmsPage.Films.THE_PHANTOM_MENACE);
        Assert.assertTrue(filmsPage.isFilmLogoShown(FilmsPage.Films.THE_PHANTOM_MENACE));
    }
}