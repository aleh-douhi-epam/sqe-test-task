import Contracts.Planets;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.ws.WebServiceException;

public class GetPlanetsTest {
    final private String expectedCountOfAllPlanets = "61";

    @Test
    public void get_AllPlanets_ReturnsTenPlanets() {
        // arrange
        final int expectedCountPerPage = 10;
        final String expectedNextPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withPageNumber("2").build();
        // act
        Planets result = SwapiRestClient.instance().getPlanets();
        // assert
        Assert.assertEquals(result.count, expectedCountOfAllPlanets);
        Assert.assertEquals(result.results.size(), expectedCountPerPage);
        Assert.assertEquals(result.next, expectedNextPage);
        Assert.assertNull(result.previous);
    }

    @Test
    public void get_PlanetsFromSecondPage_ReturnsSiblingPages() {
        // arrange
        final int expectedCountPerPage = 10;
        final String expectedNextPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withPageNumber("3").build();
        final String expectedPreviousPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withPageNumber("1").build();
        // act
        Planets result = SwapiRestClient.instance().getPlanets("2", null);
        // assert
        Assert.assertEquals(result.next, expectedNextPage);
        Assert.assertEquals(result.previous, expectedPreviousPage);
        Assert.assertEquals(result.results.size(), expectedCountPerPage);
    }

    @Test
    public void get_PlanetsFromLastPage_ReturnsSiblingPages() {
        // arrange
        final int expectedCountPerPage = 1;
        final String expectedPreviousPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withPageNumber("6").build();
        // act
        Planets result = SwapiRestClient.instance().getPlanets("7", null);
        // assert
        Assert.assertNull(result.next);
        Assert.assertEquals(result.previous, expectedPreviousPage);
        Assert.assertEquals(result.results.size(), expectedCountPerPage);
    }

    @DataProvider(name = "search-data-provider")
    public Object[][] searchDataProviderMethod() {
        return new Object[][] { { "co", 4 }, { "test", 0 } };
    }

    @Test(dataProvider = "search-data-provider")
    public void get_PlanetsBySearch_ReturnsPlanetsContainingString(String searchQuery, int expectedCountOfPlanets) {
        // act
        Planets result = SwapiRestClient.instance().getPlanets(null, searchQuery);
        // assert
        Assert.assertEquals(result.count, Integer.toString(expectedCountOfPlanets));
        Assert.assertEquals(result.results.size(), expectedCountOfPlanets);
        Assert.assertNull(result.next);
        Assert.assertNull(result.previous);
        Assert.assertTrue(result.results.stream().allMatch((planet) -> planet.name.toLowerCase().contains(searchQuery)));
    }

    @DataProvider(name = "page-data-provider")
    public Object[][] pageDataProviderMethod() {
        return new Object[][] { { "8", null }, { "a", null }, { "2", "co" } };
    }

    @Test(dataProvider = "page-data-provider")
    public void get_PlanetsFromNotExistingPage_ReturnsNotFound(String pageNumber, String searchQuery) {
        // arrange
        final String expectedStatusCode = "404";
        // act
        WebServiceException ex = Assert.expectThrows(WebServiceException.class, () -> SwapiRestClient.instance().getPlanets(pageNumber, searchQuery));
        // assert
        Assert.assertEquals(ex.getMessage(), expectedStatusCode);
    }

    @Test
    public void get_PlanetsFromSecondPageWithSearchQuery_ReturnsPlanetsContainingString() {
        // arrange
        final String expectedCountOfAllPlanets = "40";
        String searchQuery = "a";
        final String expectedNextPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withPageNumber("3").withSearchQuery("a").build();
        final String expectedPreviousPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withPageNumber("1").withSearchQuery("a").build();
        // act
        Planets result = SwapiRestClient.instance().getPlanets("2", searchQuery);
        // assert
        Assert.assertEquals(result.count, expectedCountOfAllPlanets);
        Assert.assertEquals(result.next, expectedNextPage);
        Assert.assertEquals(result.previous, expectedPreviousPage);
        Assert.assertTrue(result.results.stream().allMatch((planet) -> planet.name.toLowerCase().contains(searchQuery)));
    }
}


