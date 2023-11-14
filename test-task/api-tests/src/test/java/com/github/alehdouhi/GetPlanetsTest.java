package com.github.alehdouhi;

import com.github.alehdouhi.builder.SwapiUriBuilder;
import com.github.alehdouhi.model.Planets;
import jakarta.xml.ws.WebServiceException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetPlanetsTest {

    @Test
    public void get_allPlanets_returnsTenPlanets() {
        // arrange
        final int expectedCountPerPage = 10;
        final String expectedNextPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withPageNumber("2").build();
        // act
        Planets result = SwapiRestClient.instance().getPlanets();
        // assert
        String expectedCountOfAllPlanets = "60";
        Assert.assertEquals(result.count, expectedCountOfAllPlanets);
        Assert.assertEquals(result.results.size(), expectedCountPerPage);
        Assert.assertEquals(result.next, expectedNextPage);
        Assert.assertNull(result.previous);
    }

    @Test
    public void get_planetsFromSecondPage_returnsSiblingPages() {
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
    public void get_planetsFromLastPage_returnsSiblingPages() {
        // arrange
        final int expectedCountPerPage = 10;
        final String expectedPreviousPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withPageNumber("5").build();
        // act
        Planets result = SwapiRestClient.instance().getPlanets("6", null);
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
    public void get_planetsBySearch_returnsPlanetsContainingString(String searchQuery, int expectedCountOfPlanets) {
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
    public void get_planetsFromNotExistingPage_returnsNotFound(String pageNumber, String searchQuery) {
        // arrange
        final String expectedStatusCode = "404";
        // act
        WebServiceException ex = Assert.expectThrows(WebServiceException.class, () -> SwapiRestClient.instance().getPlanets(pageNumber, searchQuery));
        // assert
        Assert.assertEquals(ex.getMessage(), expectedStatusCode);
    }

    @Test
    public void get_planetsFromSecondPageWithSearchQuery_returnsPlanetsContainingString() {
        // arrange
        final String expectedCountOfAllPlanets = "39";
        String searchQuery = "a";
        final String expectedNextPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withSearchQuery("a").withPageNumber("3").build();
        final String expectedPreviousPage = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withSearchQuery("a").withPageNumber("1").build();
        // act
        Planets result = SwapiRestClient.instance().getPlanets("2", searchQuery);
        // assert
        Assert.assertEquals(result.count, expectedCountOfAllPlanets);
        Assert.assertEquals(result.next, expectedNextPage);
        Assert.assertEquals(result.previous, expectedPreviousPage);
        Assert.assertTrue(result.results.stream().allMatch((planet) -> planet.name.toLowerCase().contains(searchQuery)));
    }
}