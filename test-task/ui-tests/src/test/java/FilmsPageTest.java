import org.testng.Assert;
import org.testng.annotations.Test;

public class FilmsPageTest extends BaseTest {
    @Test
    public void filmsPage_openPage_sectionsAreShown() {
        // arrange
        FilmsPage filmsPage = new FilmsPage(getDriver());
        // act
        filmsPage.open();
        // assert
        Assert.assertEquals(filmsPage.getFilmsCount(), 11);
    }

    @Test
    public void filmsPage_selectFilm_filmPageIsShown() {
        // arrange
        FilmsPage filmsPage = new FilmsPage(getDriver());
        filmsPage.open();
        // act
        filmsPage.selectFilmFromPage(FilmsPage.Films.THE_PHANTOM_MENACE);
        // assert
        Assert.assertTrue(filmsPage.isFilmPosterShown(FilmsPage.Films.THE_PHANTOM_MENACE));
    }
}